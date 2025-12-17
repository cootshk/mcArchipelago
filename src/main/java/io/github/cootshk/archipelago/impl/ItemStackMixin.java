package io.github.cootshk.archipelago.impl;

public interface ItemStackMixin {
    @SuppressWarnings("unused")
    void archipelago$updateLore(); // somehow this has no usages when it's literally used in {@link ItemManager}
}
