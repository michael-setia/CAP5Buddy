package seedu.address.testutil;

import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;

/**
 * A utility class to help with building Person objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULENAME = "CS2103T";
    public static final String DEFAULT_ZOOMLINK = "www.zoom.us";
    public static final double DEFAULT_MODULARCREDITS = 4.0;

    private ModuleName moduleName;
    private ZoomLink zoomLink;
    private ModularCredits modularCredits;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULENAME);
        zoomLink = new ZoomLink(DEFAULT_ZOOMLINK);
        modularCredits = new ModularCredits(DEFAULT_MODULARCREDITS);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getName();

    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code Module} that we are building.
     */
    public ModuleBuilder withZoomLink(String zoomLink) {
        this.zoomLink = new ZoomLink(zoomLink);
        return this;
    }

    public Module build() {
        return new Module(moduleName, zoomLink, modularCredits);
    }

}
