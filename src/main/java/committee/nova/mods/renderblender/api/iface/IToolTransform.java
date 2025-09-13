package committee.nova.mods.renderblender.api.iface;

import committee.nova.mods.renderblender.api.client.util.TransformUtils;
import committee.nova.mods.renderblender.api.client.model.PerspectiveModelState;

public interface IToolTransform {
    
    default PerspectiveModelState getToolTransform() {
        return TransformUtils.DEFAULT_TOOL;
    }

}
