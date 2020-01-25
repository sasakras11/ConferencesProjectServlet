package app.model.user;

public interface Privileges {
    public abstract boolean ableToChangeName();
    public abstract boolean ableToSuggestName();
    public abstract boolean ableToChangeDate();
    public abstract boolean ableToChangeLocation();

}
