![](https://media.forgecdn.net/attachments/description/null/description_4cc8a546-1409-4ee8-b1c9-d110b4c91dbc.png)
<br>
***
# 📕作用 <br>
这是一个源自无尽贪婪:重生的库，其中包括模组中使用的渲染API，并且已经基于该API创建了几个预制渲染。
***
## 🔎如何使用? <br>
这个库准备了大量的API和十个已完成的渲染供你直接使用。
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
#### 如果你的物品是工具类的话就实现这个接口:
`public class BlazeSwordItem extends SwordItem implements IToolTransform `