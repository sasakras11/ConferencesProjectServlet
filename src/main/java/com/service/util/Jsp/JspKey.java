package com.service.util.Jsp;

import com.entity.Role;

import java.util.Objects;

public class JspKey {
    private final Role role;
    private final Stage stage;

    public JspKey(Role role, Stage stage) {
        this.role = role;
        this.stage = stage;
    }

    public Role getRole() {
        return role;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JspKey jspKey = (JspKey) o;
        return role == jspKey.role &&
                stage == jspKey.stage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, stage);
    }
}
