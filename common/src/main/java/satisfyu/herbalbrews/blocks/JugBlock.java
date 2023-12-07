package satisfyu.herbalbrews.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JugBlock extends Block {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static VoxelShape SHAPE = Shapes.box(0.125, 0, 0.125, 0.875, 0.875, 0.875);

    public JugBlock(Properties settings) {
        super(settings);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.875, 0.8125));
        return shape;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("tooltip.herbalbrews.jug_1").withStyle(ChatFormatting.ITALIC, ChatFormatting.YELLOW));
        tooltip.add(Component.translatable("tooltip.herbalbrews.jug_2").withStyle(ChatFormatting.ITALIC, ChatFormatting.YELLOW));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.herbalbrews.canbeplaced").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }
}
