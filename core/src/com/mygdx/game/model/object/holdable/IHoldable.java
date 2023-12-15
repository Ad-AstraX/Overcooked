package com.mygdx.game.model.object.holdable;

import com.mygdx.game.model.Player;

public interface IHoldable {
    boolean pickup(Player player);
    String getSubclassTypeName();
}
