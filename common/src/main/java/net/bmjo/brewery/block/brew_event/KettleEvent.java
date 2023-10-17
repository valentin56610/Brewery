package net.bmjo.brewery.block.brew_event;

import net.bmjo.brewery.block.brewingstation.BrewKettleBlock;
import net.bmjo.brewery.block.property.Liquid;
import net.bmjo.brewery.registry.BlockStateRegistry;
import net.bmjo.brewery.util.BreweryIdentifier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class KettleEvent extends BrewEvent {
    protected KettleEvent() {
        super(new BreweryIdentifier("kettle"));
    }

    @Override
    public void start(Set<BlockPos> components, Level level) {
        if (components == null || level == null) return;
        BlockPos basinPos = BrewHelper.getBlock(BrewKettleBlock.class, components, level);
        if (basinPos != null) {
            BlockState basinState = level.getBlockState(basinPos);
            level.setBlock(basinPos, basinState.setValue(BlockStateRegistry.LIQUID, Liquid.OVERFLOWING), 3);
        }
    }

    @Override
    public boolean isFinish(Set<BlockPos> components, Level level) {
        if (components == null || level == null) return true;
        BlockPos basinPos = BrewHelper.getBlock(BrewKettleBlock.class, components, level);
        if (basinPos != null) {
            BlockState basinState = level.getBlockState(basinPos);
            return basinState.getValue(BlockStateRegistry.LIQUID) != Liquid.OVERFLOWING;
        }
        return true;
    }
}
