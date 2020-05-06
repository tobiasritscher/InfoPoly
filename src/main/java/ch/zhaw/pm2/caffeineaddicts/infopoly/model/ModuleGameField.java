package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

public class ModuleGameField extends GameField {
    private final int fieldPrice;
    private final int fieldMoneyCharge;
    private final int creditsGain;
    private int fieldOwnerId;

    public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName, int fieldPrice, int fieldMoneyCharge, int creditsGain) {
        super(fieldId, fieldType, fieldName);
        this.fieldPrice = fieldPrice;
        this.fieldMoneyCharge = fieldMoneyCharge;
        this.creditsGain = creditsGain;
        this.fieldOwnerId = -1;
    }

    public int getFieldMoneyCharge() {
        return fieldMoneyCharge;
    }

    public boolean fieldHasOwner() {
        return (fieldOwnerId == -1) ? false : true;
    }

    public int getFieldOwnerId() {
        return fieldOwnerId;
    }

    public void setFieldOwner(int fieldOwnerId) {
        if (fieldHasOwner()) {
            throw new RuntimeException("invalid operation: the field already has owner");
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
