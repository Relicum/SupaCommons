package com.supaham.commons.bukkit.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.supaham.commons.utils.NumberUtils.roundExact;
import static com.supaham.commons.utils.StringUtils.checkNotNullOrEmpty;
import static java.lang.Double.parseDouble;

import com.google.common.base.Preconditions;

import com.supaham.commons.utils.RandomUtils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import pluginbase.minecraft.location.Coordinates;

/**
 * Utility methods for working with {@link Vector} instances. This class contains methods such as
 * {@link #deserialize(String)}, {@link #serialize(Vector)}, and more.
 *
 * @since 0.1
 */
public class VectorUtils {

  /**
   * Deserializes a {@link String} to represent a {@link Vector}. <p>
   * LocationUtils.deserialize("123.0 64.0 124.5") = {@link Vector}(123.0D, 64.0D, 124.5D) <br />
   * 
   * LocationUtils.deserialize("123.0 64.0") = {@link IllegalArgumentException} too few args
   * <br />
   *
   * LocationUtils.deserialize("123.0 64.0 124.5 1") = {@link
   * IllegalArgumentException} too many args <br /> </p>
   *
   * @param string string representing to deserialize
   *
   * @return returns the deserialized {@link Location}
   *
   * @throws NullPointerException thrown if the world in the {@code string} is null
   * @throws IllegalArgumentException thrown if the {@code string} is in the incorrect format
   */
  @Nonnull
  public static Vector deserialize(@Nonnull String string) throws NullPointerException {
    checkNotNullOrEmpty(string);
    String[] split = string.split("\\s+");
    checkArgument(split.length == 3, "string is in an invalid format.");
    return new Vector(parseDouble(split[0]), parseDouble(split[1]), parseDouble(split[2]));
  }

  /**
   * Serializes a {@link Vector} in the form of 'x y z'. The x, y, and z
   * coordinates are rounded to <em>two</em> decimal places.
   *
   * @param vector vector to serialize
   *
   * @return serialized {@code vector}
   */
  public static String serialize(Vector vector) {
    return roundExact(2, vector.getX()) + " "
           + roundExact(2, vector.getY()) + " "
           + roundExact(2, vector.getZ());
  }
  
  public static Vector getRandomVectorWithin(Vector min, Vector max) {
    return new Vector(RandomUtils.nextInt(min.getBlockX(), max.getBlockX()),
                      RandomUtils.nextInt(min.getBlockY(), max.getBlockY()),
                      RandomUtils.nextInt(min.getBlockY(), max.getBlockY()));
  }
  
  /**
   * Checks if a {@link Vector} is within two other {@link Vector}s.
   *
   * @param test vector to test.
   * @param min  minimum point of a cuboid region.
   * @param max  maximum point of a cuboid region.
   * @return whether the {@code test} vector is within the {@code min} and {@code max} vectors.
   */
  public static boolean isWithin(Vector test, Vector min, Vector max) {
    Preconditions.checkNotNull(test);
    Preconditions.checkNotNull(min);
    Preconditions.checkNotNull(max);

    double x = test.getX();
    double y = test.getY();
    double z = test.getZ();

    return x >= min.getBlockX() && x < max.getBlockX() + 1 &&
           y >= min.getBlockY() && y < max.getBlockY() + 1 &&
           z >= min.getBlockZ() && z < max.getBlockZ() + 1;
  }
  
  /**
   * Checks if two {@link Vector} instances are within the same block. If both of them are null,
   * true is returned.
   *
   * @param o  first {@link Coordinates} to check
   * @param o2 second {@link Coordinates} to check
   * @return true if {@code o} and {@code o2} are the same block
   */
  public static boolean isSameBlock(@Nullable Vector o, @Nullable Vector o2) {
    return o == null && o2 == null ||
           (o != null && o2 != null) && (o.getBlockX() == o2.getBlockX()) && (o.getBlockY() == o2
               .getBlockY()) &&
           (o.getBlockZ() == o2.getBlockZ());
  }
}
