package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

public abstract class GameField {
    private final int fieldId;
    private final String fieldName;
    private final IntegerProperty ownerProperty = new SimpleIntegerProperty(-1);
    private Player owner = null;

    public GameField(int fieldId, String fieldName) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
    }

    public IntegerProperty getOwnerProperty() {
        return ownerProperty;
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * Sets a new owner and changes the ownerProperty to the according playerNumber.
     *
     * @param owner The Player object which is subject to change.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        ownerProperty.setValue(owner.getPlayerId());
    }

    public boolean hasOwner() {
        return owner != null;
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
                fieldName.equals(gameField.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldId, fieldName);
    }

    @Override
    public String toString() {
        return String.format("%d %s", fieldId, fieldName);
    }
}
