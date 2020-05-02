public class Config {
    public static final int START_MONEY = 100;
    //clockwise
    FieldType[] gameBoard = {FieldType.START, FieldType.MODULE, FieldType.MODULE, FieldType.MODULE,};

    public enum FieldType {
        MODULE("Module"), STARTUP("Startup"),

        JOB("Job"), CHANCE("Chance"),

        START("Start"), SCHOLARSHIP("Scholarship"), PARTY("Party"), FEE("FEE");

        private final String fieldType;

        FieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        @Override
        public String toString() {
            return fieldType;
        }
    }
}
