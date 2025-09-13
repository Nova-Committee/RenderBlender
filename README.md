![](https://media.forgecdn.net/attachments/description/null/description_4cc8a546-1409-4ee8-b1c9-d110b4c91dbc.png)
<br>
***
# 📕Function <br>
This is a library derived from Re:Avaritia, which includes the rendering API used in Re:Avaritia, and several prefabricated renderings have been created based on this API.
***
## 🔎How Use It? <br>
This Lib has prepared a huge API and ten completed Render that you can use directly.
### **Json:**
```json5
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "minecraft:item/book"
  },
  "loader": "renderblender:glow_edge",
  "glow_edge": {
    "color": 16711680,
    "width": 1.0,
    "offset": -0.02
  }
}
```
```json5
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "renderblender:item/armor/helmet/layer_0"
  },
  "loader": "renderblender:cosmic",
  "cosmic": {
    "mask": "renderblender:mask/infinity_helmet_mask"
  }
}
```
#### If your item is Tool,you can use this:
`public class BlazeSwordItem extends SwordItem implements IToolTransform `