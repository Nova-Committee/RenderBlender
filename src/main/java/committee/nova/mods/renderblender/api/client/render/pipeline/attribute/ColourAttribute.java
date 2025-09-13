package committee.nova.mods.renderblender.api.client.render.pipeline.attribute;


import committee.nova.mods.renderblender.api.client.render.CCRenderState;
import committee.nova.mods.renderblender.api.client.render.pipeline.VertexAttribute;
import committee.nova.mods.renderblender.api.client.util.colour.ColourRGBA;

/**
 * Sets colour in CCRS to the specified colour in the model.
 */
public class ColourAttribute extends VertexAttribute<int[]> {

    public static final AttributeKey<int[]> attributeKey = AttributeKey.create("colour", int[]::new);

    private int[] colourRef;

    public ColourAttribute() {
        super(attributeKey);
    }

    @Override
    public boolean load(CCRenderState ccrs) {
        colourRef = ccrs.model.getAttribute(attributeKey);
        return colourRef != null || !ccrs.model.hasAttribute(attributeKey);
    }

    @Override
    public void operate(CCRenderState ccrs) {
        if (colourRef != null) {
            ccrs.colour = ColourRGBA.multiply(ccrs.baseColour, colourRef[ccrs.vertexIndex]);
        } else {
            ccrs.colour = ccrs.baseColour;
        }
    }
}
