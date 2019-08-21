package ch.uhc_yetis.nightmanager.web.rest;

import ch.uhc_yetis.nightmanager.NightmanagerApp;
import ch.uhc_yetis.nightmanager.domain.Game;
import ch.uhc_yetis.nightmanager.repository.GameRepository;
import ch.uhc_yetis.nightmanager.service.GameService;
import ch.uhc_yetis.nightmanager.service.dto.GameDTO;
import ch.uhc_yetis.nightmanager.service.mapper.GameMapper;
import ch.uhc_yetis.nightmanager.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static ch.uhc_yetis.nightmanager.web.rest.TestUtil.sameInstant;
import static ch.uhc_yetis.nightmanager.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.uhc_yetis.nightmanager.domain.enumeration.GameState;
import ch.uhc_yetis.nightmanager.domain.enumeration.GameType;
/**
 * Integration tests for the {@link GameResource} REST controller.
 */
@SpringBootTest(classes = NightmanagerApp.class)
public class GameResourceIT {

    private static final Integer DEFAULT_GOALS_HOME = 1;
    private static final Integer UPDATED_GOALS_HOME = 2;
    private static final Integer SMALLER_GOALS_HOME = 1 - 1;

    private static final Integer DEFAULT_GOALS_GUEST = 1;
    private static final Integer UPDATED_GOALS_GUEST = 2;
    private static final Integer SMALLER_GOALS_GUEST = 1 - 1;

    private static final GameState DEFAULT_STATE = GameState.OPEN;
    private static final GameState UPDATED_STATE = GameState.DONE;

    private static final Boolean DEFAULT_PLACEHOLDER = false;
    private static final Boolean UPDATED_PLACEHOLDER = true;

    private static final ZonedDateTime DEFAULT_START = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_START = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final Long DEFAULT_DURATION = 1L;
    private static final Long UPDATED_DURATION = 2L;
    private static final Long SMALLER_DURATION = 1L - 1L;

    private static final GameType DEFAULT_TYPE = GameType.GROUP_STAGE;
    private static final GameType UPDATED_TYPE = GameType.SEMI_FINAL;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private GameService gameService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGameMockMvc;

    private Game game;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GameResource gameResource = new GameResource(gameService);
        this.restGameMockMvc = MockMvcBuilders.standaloneSetup(gameResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Game createEntity(EntityManager em) {
        Game game = new Game()
            .goalsHome(DEFAULT_GOALS_HOME)
            .goalsGuest(DEFAULT_GOALS_GUEST)
            .state(DEFAULT_STATE)
            .placeholder(DEFAULT_PLACEHOLDER)
            .start(DEFAULT_START)
            .duration(DEFAULT_DURATION)
            .type(DEFAULT_TYPE);
        return game;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Game createUpdatedEntity(EntityManager em) {
        Game game = new Game()
            .goalsHome(UPDATED_GOALS_HOME)
            .goalsGuest(UPDATED_GOALS_GUEST)
            .state(UPDATED_STATE)
            .placeholder(UPDATED_PLACEHOLDER)
            .start(UPDATED_START)
            .duration(UPDATED_DURATION)
            .type(UPDATED_TYPE);
        return game;
    }

    @BeforeEach
    public void initTest() {
        game = createEntity(em);
    }

    @Test
    @Transactional
    public void createGame() throws Exception {
        int databaseSizeBeforeCreate = gameRepository.findAll().size();

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);
        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isCreated());

        // Validate the Game in the database
        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeCreate + 1);
        Game testGame = gameList.get(gameList.size() - 1);
        assertThat(testGame.getGoalsHome()).isEqualTo(DEFAULT_GOALS_HOME);
        assertThat(testGame.getGoalsGuest()).isEqualTo(DEFAULT_GOALS_GUEST);
        assertThat(testGame.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testGame.isPlaceholder()).isEqualTo(DEFAULT_PLACEHOLDER);
        assertThat(testGame.getStart()).isEqualTo(DEFAULT_START);
        assertThat(testGame.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testGame.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createGameWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gameRepository.findAll().size();

        // Create the Game with an existing ID
        game.setId(1L);
        GameDTO gameDTO = gameMapper.toDto(game);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Game in the database
        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGoalsHomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = gameRepository.findAll().size();
        // set the field null
        game.setGoalsHome(null);

        // Create the Game, which fails.
        GameDTO gameDTO = gameMapper.toDto(game);

        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGoalsGuestIsRequired() throws Exception {
        int databaseSizeBeforeTest = gameRepository.findAll().size();
        // set the field null
        game.setGoalsGuest(null);

        // Create the Game, which fails.
        GameDTO gameDTO = gameMapper.toDto(game);

        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = gameRepository.findAll().size();
        // set the field null
        game.setState(null);

        // Create the Game, which fails.
        GameDTO gameDTO = gameMapper.toDto(game);

        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlaceholderIsRequired() throws Exception {
        int databaseSizeBeforeTest = gameRepository.findAll().size();
        // set the field null
        game.setPlaceholder(null);

        // Create the Game, which fails.
        GameDTO gameDTO = gameMapper.toDto(game);

        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = gameRepository.findAll().size();
        // set the field null
        game.setStart(null);

        // Create the Game, which fails.
        GameDTO gameDTO = gameMapper.toDto(game);

        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = gameRepository.findAll().size();
        // set the field null
        game.setType(null);

        // Create the Game, which fails.
        GameDTO gameDTO = gameMapper.toDto(game);

        restGameMockMvc.perform(post("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGames() throws Exception {
        // Initialize the database
        gameRepository.saveAndFlush(game);

        // Get all the gameList
        restGameMockMvc.perform(get("/api/games?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(game.getId().intValue())))
            .andExpect(jsonPath("$.[*].goalsHome").value(hasItem(DEFAULT_GOALS_HOME)))
            .andExpect(jsonPath("$.[*].goalsGuest").value(hasItem(DEFAULT_GOALS_GUEST)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].placeholder").value(hasItem(DEFAULT_PLACEHOLDER.booleanValue())))
            .andExpect(jsonPath("$.[*].start").value(hasItem(sameInstant(DEFAULT_START))))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getGame() throws Exception {
        // Initialize the database
        gameRepository.saveAndFlush(game);

        // Get the game
        restGameMockMvc.perform(get("/api/games/{id}", game.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(game.getId().intValue()))
            .andExpect(jsonPath("$.goalsHome").value(DEFAULT_GOALS_HOME))
            .andExpect(jsonPath("$.goalsGuest").value(DEFAULT_GOALS_GUEST))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.placeholder").value(DEFAULT_PLACEHOLDER.booleanValue()))
            .andExpect(jsonPath("$.start").value(sameInstant(DEFAULT_START)))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGame() throws Exception {
        // Get the game
        restGameMockMvc.perform(get("/api/games/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGame() throws Exception {
        // Initialize the database
        gameRepository.saveAndFlush(game);

        int databaseSizeBeforeUpdate = gameRepository.findAll().size();

        // Update the game
        Game updatedGame = gameRepository.findById(game.getId()).get();
        // Disconnect from session so that the updates on updatedGame are not directly saved in db
        em.detach(updatedGame);
        updatedGame
            .goalsHome(UPDATED_GOALS_HOME)
            .goalsGuest(UPDATED_GOALS_GUEST)
            .state(UPDATED_STATE)
            .placeholder(UPDATED_PLACEHOLDER)
            .start(UPDATED_START)
            .duration(UPDATED_DURATION)
            .type(UPDATED_TYPE);
        GameDTO gameDTO = gameMapper.toDto(updatedGame);

        restGameMockMvc.perform(put("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isOk());

        // Validate the Game in the database
        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeUpdate);
        Game testGame = gameList.get(gameList.size() - 1);
        assertThat(testGame.getGoalsHome()).isEqualTo(UPDATED_GOALS_HOME);
        assertThat(testGame.getGoalsGuest()).isEqualTo(UPDATED_GOALS_GUEST);
        assertThat(testGame.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testGame.isPlaceholder()).isEqualTo(UPDATED_PLACEHOLDER);
        assertThat(testGame.getStart()).isEqualTo(UPDATED_START);
        assertThat(testGame.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testGame.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingGame() throws Exception {
        int databaseSizeBeforeUpdate = gameRepository.findAll().size();

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGameMockMvc.perform(put("/api/games")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Game in the database
        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGame() throws Exception {
        // Initialize the database
        gameRepository.saveAndFlush(game);

        int databaseSizeBeforeDelete = gameRepository.findAll().size();

        // Delete the game
        restGameMockMvc.perform(delete("/api/games/{id}", game.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Game> gameList = gameRepository.findAll();
        assertThat(gameList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Game.class);
        Game game1 = new Game();
        game1.setId(1L);
        Game game2 = new Game();
        game2.setId(game1.getId());
        assertThat(game1).isEqualTo(game2);
        game2.setId(2L);
        assertThat(game1).isNotEqualTo(game2);
        game1.setId(null);
        assertThat(game1).isNotEqualTo(game2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GameDTO.class);
        GameDTO gameDTO1 = new GameDTO();
        gameDTO1.setId(1L);
        GameDTO gameDTO2 = new GameDTO();
        assertThat(gameDTO1).isNotEqualTo(gameDTO2);
        gameDTO2.setId(gameDTO1.getId());
        assertThat(gameDTO1).isEqualTo(gameDTO2);
        gameDTO2.setId(2L);
        assertThat(gameDTO1).isNotEqualTo(gameDTO2);
        gameDTO1.setId(null);
        assertThat(gameDTO1).isNotEqualTo(gameDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(gameMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(gameMapper.fromId(null)).isNull();
    }
}
