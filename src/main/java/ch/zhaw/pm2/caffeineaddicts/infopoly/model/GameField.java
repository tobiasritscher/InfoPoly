package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

public class GameField {

    private final int fieldId;
    private final Config.FieldType fieldType;
    private final String fieldName;

    public GameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        this.fieldId = fieldId;
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public Config.FieldType getFieldType() {
        return fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldId() {
        return fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameField gameField = (GameField) o;
        return fieldId == gameField.fieldId &&
                fieldType == gameField.fieldType &&
                fieldName.equals(gameField.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldId, fieldType, fieldName);
    }

    public static class ModuleGameField extends GameField {
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

    public static class StartupGameField {
        private final int moneyNeeded;
        private final int moneyPayout;
        private IntegerProperty founderId = new SimpleIntegerProperty();

        public StartupGameField(int moneyNeeded, int moneyPayout) {
            this.moneyNeeded = moneyNeeded;
            this.moneyPayout = moneyPayout;
            this.founderId.set(-1);
        }

        public boolean isLaunched() {
            return (founderId.get() == -1) ? false : true;
        }

        /**
         * Note: before calling call @{@link StartupGameField#isLaunched()}
         * Will throw exception if not launched.
         *
         * @return
         */
        public int getFounderId() {
            if (!isLaunched()) {
                throw new RuntimeException("invalid operation: field already has founder");
            }
            return founderId.get();
        }

        public void setFounderId(int founderId) {
            if (isLaunched()) {
                throw new RuntimeException("invalid operation: field already has founder");
            }
            this.founderId.set(founderId);
        }

        public int getMoneyNeeded() {
            return moneyNeeded;
        }

        public int getMoneyPayout() {
            if (!isLaunched()) {
                throw new RuntimeException("invalid operation: no may money may obtained, startup is not launched yet.");
            }
            return moneyPayout;
        }

        public IntegerProperty founderIdProperty() {
            return founderId;
        }
    }
}
