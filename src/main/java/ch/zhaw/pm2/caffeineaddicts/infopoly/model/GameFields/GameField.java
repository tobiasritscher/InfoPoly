package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

public abstract class GameField {
    private final int fieldId;
    private final Config.FieldType fieldType;
    private final String fieldName;
    private Player owner = null;
    private IntegerProperty ownerProperty = new SimpleIntegerProperty(0);

    public GameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        this.fieldId = fieldId;
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public IntegerProperty getOwnerProperty() {
        return ownerProperty;
    }

    public Config.FieldType getFieldType() {
        return fieldType;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    /**
     * Sets a new owner and changes the ownerProperty to the according playerNumber.
     *
     * @param owner The Player object which is subject to change.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        ownerProperty.setValue(owner.getPlayerNumber());
    }

    /**
     * Resets the field owner and changes the ownerProperty to zero.
     */
    public void resetOwner() {
        owner = null;
        ownerProperty.setValue(0);
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public abstract void action(Player currentPlayer);

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
}
