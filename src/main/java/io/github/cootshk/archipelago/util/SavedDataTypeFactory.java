package io.github.cootshk.archipelago.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

public class SavedDataTypeFactory {
    /**
     * Builds a SavedDataType instance. {@link SavedDataType(String, Supplier, Codec, DataFixTypes)}'s constructor isn't @{@link Nullable} annotated, so this factory method adds those annotations.
     * @see SavedDataType(String, Supplier, Codec, DataFixTypes)
     */
    @Contract(value = "_, _, _, _ -> new", pure = true)
    @SuppressWarnings("DataFlowIssue")
    public static <T extends SavedData> @NotNull SavedDataType<@NotNull T> build(String id, Supplier<T> supplier, Codec<T> codec, @Nullable DataFixTypes dataFixTypes) {
        return new SavedDataType<>(
                id,
                supplier,
                codec,
                dataFixTypes
        );
    }
}
