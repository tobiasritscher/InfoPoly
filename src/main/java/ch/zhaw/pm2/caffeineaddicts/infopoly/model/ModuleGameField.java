package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ModuleGameField extends GameField {
    private final int fieldPrice;
    private final int fieldMoneyChargeProperty;
    private final int creditsGain;
    private IntegerProperty fieldOwnerId = new SimpleIntegerProperty();

    public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName, int fieldPrice, int fieldMoneyCharge, int creditsGain) {
        super(fieldId, fieldType, fieldName);
        this.fieldPrice = fieldPrice;
        this.fieldMoneyChargeProperty = fieldMoneyCharge;
        this.creditsGain = creditsGain;
        this.fieldOwnerId.set(-1);
    }

    public int getFieldMoneyCharge() {
        return fieldMoneyChargeProperty;
    }

    public boolean fieldHasOwner() {
        return (fieldOwnerId.get() == -1) ? false : true;
    }

    public int getFieldOwnerId() {
        return fieldOwnerId.get();
    }

    public IntegerProperty fieldOwnerIdProperty() {
        return fieldOwnerId;
    }

    public void setFieldOwner(int fieldOwnerId) {
        if (fieldHasOwner()) {
            throw new RuntimeException("invalid operation: the field already has owner");
        }
        this.fieldOwnerId.set(fieldOwnerId);
    }

    public int getFieldPrice() {
        return fieldPrice;
    }

    public int getCreditsGain() {
        return creditsGain;
    }
}
