package net.PS.phenomena.mixin;//package net.Davidak.phenomena.mixin;
//
//import net.minecraft.client.gui.components.EditBox;
//import net.minecraft.client.gui.screens.inventory.StructureBlockEditScreen;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
///**
// * Make Structure Blocks no longer capped at 64 characters which makes it harder to
// * load some bigger structures
// *
// * Mixin priority set to 995 so other mixins here will override this mixin by default if they edit the same field.
// */
////TODO fix(not working)
//@Mixin(value = StructureBlockEditScreen.class, priority = 1)
//public class StructureBlockScreenMixin {
//
//    @Shadow
//    private EditBox nameEdit;
//
//    @Inject(method = "init",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/EditBox;setMaxLength(I)V", ordinal = 0, shift = At.Shift.AFTER))
//    private void phenomena_makeFileNameLonger(CallbackInfo ci) {
//        nameEdit.setMaxLength(256);
//    }
//}