package ch.uhc_yetis.nightmanager.web.rest;

import ch.uhc_yetis.nightmanager.NightmanagerApp;
import ch.uhc_yetis.nightmanager.domain.Hall;
import ch.uhc_yetis.nightmanager.repository.HallRepository;
import ch.uhc_yetis.nightmanager.service.HallService;
import ch.uhc_yetis.nightmanager.service.dto.HallDTO;
import ch.uhc_yetis.nightmanager.service.mapper.HallMapper;
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
import java.util.List;

import static ch.uhc_yetis.nightmanager.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link HallResource} REST controller.
 */
@SpringBootTest(classes = NightmanagerApp.class)
public class HallResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private HallService hallService;

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

    private MockMvc restHallMockMvc;

    private Hall hall;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HallResource hallResource = new HallResource(hallService);
        this.restHallMockMvc = MockMvcBuilders.standaloneSetup(hallResource)
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
    public static Hall createEntity(EntityManager em) {
        Hall hall = new Hall()
            .name(DEFAULT_NAME);
        return hall;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hall createUpdatedEntity(EntityManager em) {
        Hall hall = new Hall()
            .name(UPDATED_NAME);
        return hall;
    }

    @BeforeEach
    public void initTest() {
        hall = createEntity(em);
    }

    @Test
    @Transactional
    public void createHall() throws Exception {
        int databaseSizeBeforeCreate = hallRepository.findAll().size();

        // Create the Hall
        HallDTO hallDTO = hallMapper.toDto(hall);
        restHallMockMvc.perform(post("/api/halls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hallDTO)))
            .andExpect(status().isCreated());

        // Validate the Hall in the database
        List<Hall> hallList = hallRepository.findAll();
        assertThat(hallList).hasSize(databaseSizeBeforeCreate + 1);
        Hall testHall = hallList.get(hallList.size() - 1);
        assertThat(testHall.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createHallWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hallRepository.findAll().size();

        // Create the Hall with an existing ID
        hall.setId(1L);
        HallDTO hallDTO = hallMapper.toDto(hall);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHallMockMvc.perform(post("/api/halls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hallDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hall in the database
        List<Hall> hallList = hallRepository.findAll();
        assertThat(hallList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = hallRepository.findAll().size();
        // set the field null
        hall.setName(null);

        // Create the Hall, which fails.
        HallDTO hallDTO = hallMapper.toDto(hall);

        restHallMockMvc.perform(post("/api/halls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hallDTO)))
            .andExpect(status().isBadRequest());

        List<Hall> hallList = hallRepository.findAll();
        assertThat(hallList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHalls() throws Exception {
        // Initialize the database
        hallRepository.saveAndFlush(hall);

        // Get all the hallList
        restHallMockMvc.perform(get("/api/halls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hall.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getHall() throws Exception {
        // Initialize the database
        hallRepository.saveAndFlush(hall);

        // Get the hall
        restHallMockMvc.perform(get("/api/halls/{id}", hall.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hall.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHall() throws Exception {
        // Get the hall
        restHallMockMvc.perform(get("/api/halls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHall() throws Exception {
        // Initialize the database
        hallRepository.saveAndFlush(hall);

        int databaseSizeBeforeUpdate = hallRepository.findAll().size();

        // Update the hall
        Hall updatedHall = hallRepository.findById(hall.getId()).get();
        // Disconnect from session so that the updates on updatedHall are not directly saved in db
        em.detach(updatedHall);
        updatedHall
            .name(UPDATED_NAME);
        HallDTO hallDTO = hallMapper.toDto(updatedHall);

        restHallMockMvc.perform(put("/api/halls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hallDTO)))
            .andExpect(status().isOk());

        // Validate the Hall in the database
        List<Hall> hallList = hallRepository.findAll();
        assertThat(hallList).hasSize(databaseSizeBeforeUpdate);
        Hall testHall = hallList.get(hallList.size() - 1);
        assertThat(testHall.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingHall() throws Exception {
        int databaseSizeBeforeUpdate = hallRepository.findAll().size();

        // Create the Hall
        HallDTO hallDTO = hallMapper.toDto(hall);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHallMockMvc.perform(put("/api/halls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hallDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hall in the database
        List<Hall> hallList = hallRepository.findAll();
        assertThat(hallList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHall() throws Exception {
        // Initialize the database
        hallRepository.saveAndFlush(hall);

        int databaseSizeBeforeDelete = hallRepository.findAll().size();

        // Delete the hall
        restHallMockMvc.perform(delete("/api/halls/{id}", hall.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Hall> hallList = hallRepository.findAll();
        assertThat(hallList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hall.class);
        Hall hall1 = new Hall();
        hall1.setId(1L);
        Hall hall2 = new Hall();
        hall2.setId(hall1.getId());
        assertThat(hall1).isEqualTo(hall2);
        hall2.setId(2L);
        assertThat(hall1).isNotEqualTo(hall2);
        hall1.setId(null);
        assertThat(hall1).isNotEqualTo(hall2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HallDTO.class);
        HallDTO hallDTO1 = new HallDTO();
        hallDTO1.setId(1L);
        HallDTO hallDTO2 = new HallDTO();
        assertThat(hallDTO1).isNotEqualTo(hallDTO2);
        hallDTO2.setId(hallDTO1.getId());
        assertThat(hallDTO1).isEqualTo(hallDTO2);
        hallDTO2.setId(2L);
        assertThat(hallDTO1).isNotEqualTo(hallDTO2);
        hallDTO1.setId(null);
        assertThat(hallDTO1).isNotEqualTo(hallDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(hallMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(hallMapper.fromId(null)).isNull();
    }
}
