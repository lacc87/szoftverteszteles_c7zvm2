package hu.unideb.inf;

import java.util.Objects;

public class RegistrationField {

    private String id;
    private String value;
    private Boolean select;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationField that = (RegistrationField) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    public RegistrationField(String id, String value) {
        this.id = id;
        this.value = value;
        this.select = false;
    }

    public RegistrationField(String id, String value, Boolean select) {
        this.id = id;
        this.value = value;
        this.select = select;
    }

    public Boolean isSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
