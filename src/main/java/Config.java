public class Config {
    public static final int START_MONEY = 100;

    public enum FieldType {
        MODULE("Module"),STARTUP("Startup"),

        JOB("Job"),CHANCE("Chance"),

        START("Start"),SCHOLARSHIP("Scholarship");

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
