package com.karbij.dinnersmp.block.custom;

import javax.annotation.Nullable;

import com.karbij.dinnersmp.block.entity.FinalMeal_Entity;

import net.minecraft.core.BlockPos;
// import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
// import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
// import net.minecraft.world.phys.shapes.CollisionContext;
// import net.minecraft.world.phys.shapes.VoxelShape;

public class FinalMealBlock extends BaseEntityBlock  {
 
 //   public static final VoxelShape SHAPE = Block.box(0, 0, 0, 10, 4, 10);

    public FinalMealBlock(Properties pProperties) {
        super(pProperties);
    }

/*     @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
*/
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new FinalMeal_Entity(pPos, pState); 
    }

//     @Override
//     @Nullable
//     public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        
        
//     }
}
