package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Category;

public class TeamDto {
    private long id;
    private String name;
    private Category category;
    private boolean placeholder;
    private int goalsShot;
    private int goalsGotten;
    private int points;


    private TeamDto() {
    }

    public int getGoalsShot() {
        return this.goalsShot;
    }

    public int getGoalsGotten() {
        return this.goalsGotten;
    }

    public long getId() {
        return this.id;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPlaceholder() {
        return this.placeholder;
    }

    public int getPoints() {
        return this.points;
    }

    public static NameBuilder withId(long id) {
        return new TeamDtoBuilder().withId(id);
    }

    public static class TeamDtoBuilder implements IDBuilder, NameBuilder, PlaceholderBuilder, GoalsGottenBuilder, GoalsShotBuilder, OptionalFieldsBuilder, CategoryBuilder, PointBuilder {


        private TeamDto teamDto;

        public TeamDtoBuilder() {
            this.teamDto = new TeamDto();
        }

        @Override
        public NameBuilder withId(long id) {
            this.teamDto.id = id;
            return this;
        }

        @Override
        public CategoryBuilder withName(String name) {
            this.teamDto.name = name;
            return this;
        }

        @Override
        public GoalsShotBuilder withPlaceholder(boolean isPlaceholder) {
            this.teamDto.placeholder = isPlaceholder;
            return this;
        }

        @Override
        public GoalsGottenBuilder withGoalsShot(int goalsShot) {
            this.teamDto.goalsShot = goalsShot;
            return this;
        }

        @Override
        public PointBuilder withGoalsGotten(int goalsGotten) {
            this.teamDto.goalsGotten = goalsGotten;
            return this;
        }

        @Override
        public TeamDto build() {
            return this.teamDto;
        }

        @Override
        public PlaceholderBuilder withCategory(Category category) {
            this.teamDto.category = category;
            return this;
        }

        @Override
        public OptionalFieldsBuilder withPoints(int points) {
            this.teamDto.points = points;
            return this;
        }
    }

    public interface IDBuilder {
        NameBuilder withId(long id);
    }

    public interface NameBuilder {
        CategoryBuilder withName(String name);
    }

    public interface CategoryBuilder {
        PlaceholderBuilder withCategory(Category category);
    }

    public interface PlaceholderBuilder {
        GoalsShotBuilder withPlaceholder(boolean isPlaceholder);
    }

    public interface GoalsShotBuilder {
        GoalsGottenBuilder withGoalsShot(int goalsShot);
    }

    public interface GoalsGottenBuilder {
        PointBuilder withGoalsGotten(int goalsGotten);
    }

    public interface PointBuilder {
        OptionalFieldsBuilder withPoints(int points);
    }

    public interface RankBuilder {
        OptionalFieldsBuilder withRank(int rank);
    }

    public interface OptionalFieldsBuilder {
        TeamDto build();
    }
}
