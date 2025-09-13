package committee.nova.mods.renderblender.api.client.shader.objects;

import com.google.common.collect.ImmutableList;
import committee.nova.mods.renderblender.api.client.shader.base.UniformPair;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;

public interface ShaderObject {

    String getName();

    ShaderType getShaderType();

    ImmutableList<UniformPair> getUniforms();

    boolean isDirty();

    void alloc();

    int getShaderID();

    void onLink(int programId);

    /**
     * Standard ShaderTypes.
     */
    enum StandardShaderType implements ShaderType {
        //@formatter:off
        VERTEX      (GL20.GL_VERTEX_SHADER         ),
        FRAGMENT    (GL20.GL_FRAGMENT_SHADER       ),
        GEOMETRY    (GL32.GL_GEOMETRY_SHADER       ),
        TESS_CONTROL(GL40.GL_TESS_CONTROL_SHADER   ),
        TESS_EVAL   (GL40.GL_TESS_EVALUATION_SHADER),
        COMPUTE     (GL43.GL_COMPUTE_SHADER        );
        //@formatter:on

        private final int glCode;

        StandardShaderType(int glCode) {
            this.glCode = glCode;
        }

        @Override
        public int getGLCode() {
            return glCode;
        }
    }

    interface ShaderType {
        int getGLCode();
    }
}
