package committee.nova.mods.renderblender.api.iface;

import committee.nova.mods.renderblender.api.client.model.PerspectiveModelState;
import committee.nova.mods.renderblender.api.client.util.TransformUtils;

public interface IBowTransform {
    default PerspectiveModelState getToolTransform() {
        return TransformUtils.DEFAULT_BOW;
    }
}
