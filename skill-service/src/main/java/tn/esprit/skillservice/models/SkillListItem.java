package tn.esprit.skillservice.models;

import tn.esprit.skillservice.entities.Skill;

public class SkillListItem {

    private Skill skill;
    private boolean isDone;

    public SkillListItem() {
    }
    public SkillListItem(Skill skill, boolean isDone) {
        this.skill = skill;
        this.isDone = isDone;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
