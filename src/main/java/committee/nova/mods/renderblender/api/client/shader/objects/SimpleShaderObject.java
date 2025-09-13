package committee.nova.mods.renderblender.api.client.shader.objects;

import committee.nova.mods.renderblender.api.client.shader.base.UniformPair;

import java.util.Collection;

/**
 * Created by covers1624 on 24/5/20.
 */
public class SimpleShaderObject extends AbstractShaderObject {

    private final String source;

    public SimpleShaderObject(String name, ShaderObject.ShaderType type, Collection<UniformPair> uniforms, String source) {
        super(name, type, uniforms);
        this.source = source;
    }

    @Override
    protected String getSource() {
        return source;
    }
}
