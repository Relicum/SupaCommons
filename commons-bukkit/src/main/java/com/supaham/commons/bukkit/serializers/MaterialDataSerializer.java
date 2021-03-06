package com.supaham.commons.bukkit.serializers;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import pluginbase.config.serializers.Serializer;

/**
 * A {@link MaterialData} serializer that serializes as "x:y" where x is {@link
 * MaterialData#getItemType()} and y is {@link MaterialData#getData()}.
 *
 * @since 0.1
 */
public class MaterialDataSerializer implements Serializer<MaterialData> {

  @Nullable
  @Override
  public Object serialize(@Nullable MaterialData object) {
    if (object == null) {
      return null;
    }
    return object.getItemType() + ":" + object.getData();
  }

  @Nullable
  @Override
  public MaterialData deserialize(@Nullable Object serialized, @NotNull Class wantedType)
      throws IllegalArgumentException {
    if (serialized == null) {
      return null;
    }
    String[] split = serialized.toString().split(":");
    return new MaterialData(Material.getMaterial(split[0]),
                            split.length >= 2 && !split[1].isEmpty() ? Byte.valueOf(split[1]) : 0);
  }
}
