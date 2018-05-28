package ch.uhc_yetis.nightmanager.service.api.teams;

public class TeamDto {
    private Long id;

    private TeamDto(){
    }

    public static OptionalFieldsBuilder withId(long id){
        return  new TeamDtoBuilder().withId(id);
    }

    public Long getId() {
        return id;
    }

    private static class TeamDtoBuilder implements TeamIdBuilder, OptionalFieldsBuilder{
        private final TeamDto teamDto;

        public TeamDtoBuilder(){
             teamDto = new TeamDto();
        }

        @Override
        public OptionalFieldsBuilder withId(long id) {
            teamDto.id=id;
            return this;
        }

        @Override
        public TeamDto build() {
            return teamDto;
        }
    }

    public interface TeamIdBuilder{
        OptionalFieldsBuilder withId(long id);
    }

    public interface OptionalFieldsBuilder{
        TeamDto build();
    }
}
