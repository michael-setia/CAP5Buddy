package seedu.address.model.module;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.Grade;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;



/**
 * Represents the Module creation class.
 */
public class Module {
    private final ModuleName name;
    private final ZoomLink zoomLink;
    private final GradeTracker gradeTracker;
    private final ModularCredits modularCredits;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param zoomLink zoom link attached to module
     * @param gradeTracker grade tracker attached to module
     * @param tags tag attached to module
     */
    public Module(ModuleName name, ZoomLink zoomLink, GradeTracker gradeTracker, Set<Tag> tags) {
        this.name = name;
        this.zoomLink = zoomLink;
        this.gradeTracker = gradeTracker;
        this.tags.addAll(tags);
        this.modularCredits = new ModularCredits();
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param zoomLink zoom link attached to module
     * @param gradeTracker grade tracker attached to module
     * @param tags tag attached to module
     */
    public Module(ModuleName name, ZoomLink zoomLink, GradeTracker gradeTracker, Set<Tag> tags,
                  ModularCredits modularCredits) {
        this.name = name;
        this.zoomLink = zoomLink;
        this.gradeTracker = gradeTracker;
        this.tags.addAll(tags);
        this.modularCredits = modularCredits;
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param zoomLink zoom link attached to module
     * @param tags tag attached to module
     */
    public Module(ModuleName name, ZoomLink zoomLink, Set<Tag> tags, ModularCredits modularCredits) {
        this.name = name;
        this.zoomLink = zoomLink;
        this.gradeTracker = new GradeTracker();
        this.tags.addAll(tags);
        this.modularCredits = modularCredits;
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param zoomLink zoom link attached to module
     */
    public Module(ModuleName name, ZoomLink zoomLink, ModularCredits modularCredits) {
        this.name = name;
        this.zoomLink = zoomLink;
        this.gradeTracker = new GradeTracker();
        this.modularCredits = modularCredits;
    }
    /**
     * Represents the module object constructor.
     */
    public Module() {
        this.name = null;
        this.zoomLink = null;
        this.gradeTracker = new GradeTracker();
        this.modularCredits = new ModularCredits();
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     */
    public Module(ModuleName name) {
        this.name = name;
        this.zoomLink = null;
        this.gradeTracker = new GradeTracker();
        this.modularCredits = new ModularCredits();
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param zoomLink zoom link attached to module
     */
    public Module(ModuleName name, ZoomLink zoomLink) {
        this.name = name;
        this.zoomLink = zoomLink;
        this.gradeTracker = new GradeTracker();
        this.modularCredits = new ModularCredits();
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param tags tag attached to module
     * @param modularCredits modular credits for module
     */
    public Module(ModuleName name, Set<Tag> tags, ModularCredits modularCredits) {
        this.name = name;
        this.zoomLink = null;
        this.gradeTracker = new GradeTracker();
        this.tags.addAll(tags);
        this.modularCredits = modularCredits;
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param gradeTracker grade tracker attached to module
     */
    public Module(ModuleName name, GradeTracker gradeTracker) {
        this.name = name;
        this.zoomLink = null;
        this.gradeTracker = gradeTracker;
        this.modularCredits = new ModularCredits();
    }

    /**
     * Returns the module name.
     * @return ModuleName module name.
     */
    public ModuleName getName() {
        assert this.name != null;
        return this.name;
    }

    /**
     * Returns the zoom link of the module.
     * @return ZoomLink zoom link.
     */
    public ZoomLink getLink() {
        return this.zoomLink;
    }

    /**
     * Adds the zoom link for this module.
     * @param zoomLink zoom link.
     * @return Module a new Module with the input zoom link.
     */
    public Module addZoomLink(ZoomLink zoomLink) {
        return new Module(this.getName(), zoomLink, this.gradeTracker, this.tags);
    }

    /**
     * Returns the grades being tracked for this module.
     *
     * @return grade tracker.
     */
    public GradeTracker getGradeTracker() {
        return gradeTracker;
    }

    /**
     * Returns the modular credits for this module
     */
    public ModularCredits getModularCredits() {
        return this.modularCredits;
    }

    /**
     * Adds an assignment to the GradeTracker of the module.
     *
     * @param assignment assignment to add to grade tracker.
     * @return Module a new module with the assignment added if it is different.
     */
    public Module addAssignment(Assignment assignment) {
        if (!gradeTracker.isDuplicateAssignment(assignment)) {
            gradeTracker.addAssignment(assignment);
            return new Module(name, zoomLink, gradeTracker, tags);
        } else {
            return this;
        }
    }

    /**
<<<<<<< HEAD
=======

>>>>>>> bdf206181bef4a2bb9f0c4672fc8eef26adb4a1b
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    /**
     * Adds a grade to the GradeTracker of the module.
     *
     * @param grade grade to add to grade tracker.
     * @return Module a new module with the grade added.
     */
    public Module addGrade(Grade grade) {
        if (Grade.isValidGrade(grade.gradeResult)) {
            gradeTracker.setGrade(grade);
            return new Module(name, zoomLink, gradeTracker, tags);
        } else {
            return this;
        }
    }

    /**
     * Returns true if both modules have the same name.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }
        return otherModule != null
                && otherModule.getName().equals(getName());
    }

    /**
     * Returns true if module is completed by checking whether the module has a completed tag.
     */
    public boolean isCompleted() {
        return this.tags.stream().map(x -> x.equals(new Tag("completed")))
                .reduce(false, (x, y) -> x || y);
    }

    @Override
    public String toString() {
        return String.format("The zoom link for %s is %s", getName(), getLink());
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getName().equals(getName())
                && otherModule.getLink().equals(getLink())
                && otherModule.getGradeTracker().equals(getGradeTracker());
    }

}
