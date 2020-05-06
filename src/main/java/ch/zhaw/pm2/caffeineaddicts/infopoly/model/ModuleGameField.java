package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

public class ModuleGameField extends GameField {
    private final int fieldPrice;
    private final int creditsGain;
    private int fieldOwnerId;

    public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName, int fieldPrice, int creditsGain) {
        super(fieldId, fieldType, fieldName);
        this.fieldPrice = fieldPrice;
        this.creditsGain = creditsGain;
        this.fieldOwnerId = -1;
    }

    public boolean fieldHasOwner() {
        return (fieldOwnerId == -1) ? true : false;
    }

    public int getFieldOwnerId() {
        return fieldOwnerId;
    }

    public void setFieldOwner(int fieldOwnerId) {
        if (fieldHasOwner()) {
            throw new RuntimeException("invalid opertaion: the feild has owner");
        }
        this.fieldOwnerId = fieldOwnerId;
    }

    public int getFieldPrice() {
        return fieldPrice;
    }

    public int getCreditsGain() {
        return creditsGain;
    }
}
