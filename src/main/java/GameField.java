public class GameField {

    private final int fieldId;
    private final int fieldType;

    public GameField(int fieldId, int fieldType) {
        this.fieldId = fieldId;
        this.fieldType = fieldType;
    }

    public int getFieldId() {
        return fieldId;
    }

    public enum FieldType{
        //todo implement
    }
}
