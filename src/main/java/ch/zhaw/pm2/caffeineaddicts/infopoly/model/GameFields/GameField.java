package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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

    Player getOwner() {
        return owner;
    }

    /**
     * Sets a new owner and changes the ownerProperty to the according playerNumber.
     *
     * @param owner The Player object which is subject to change.
     */
    void setOwner(Player owner) {
        this.owner = owner;
        ownerProperty.setValue(owner.getPlayerId());
    }

    boolean hasOwner() {
        return owner != null;
    }

    /**
     * Resets the field owner and changes the ownerProperty to zero.
     */
    void resetOwner() {
        owner = null;
        ownerProperty.setValue(0);
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldId() {
        return fieldId;
    }

    /**
     * The action each subclass has to implement
     * this method will be called, when somebody enters the field
     *
     * @param currentPlayer != 0, the player who is standing on the field
     */
    public abstract void action(Player currentPlayer);

    /**
     * compares the parameters of two fields to determine if they are equal
     *
     * @param o the object to compare
     * @return true if the two objects are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameField gameField = (GameField) o;
        return fieldId == gameField.fieldId &&
                fieldName.equals(gameField.fieldName);
    }

    /**
     * transfers the data of the field in to a string.
     *
     * @return the fieldId + fieldName in a string
     */
    @Override
    public String toString() {
        return String.format("%d %s", fieldId, fieldName);
    }
}
