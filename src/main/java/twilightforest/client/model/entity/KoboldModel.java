// Date: 6/11/2012 3:12:45 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package twilightforest.client.model.entity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import twilightforest.entity.monster.Kobold;

public class KoboldModel extends HumanoidModel<Kobold> {

	final ModelPart jaw;
	boolean isJumping;

	public KoboldModel(ModelPart root) {
		super(root);
		this.isJumping = false;
		this.jaw = getHead().getChild("jaw");
	}

	public static LayerDefinition create() {
		MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
		PartDefinition definition = mesh.getRoot();

		var head = definition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-3.5F, -7F, -3F, 7, 6, 6),
				PartPose.offset(0F, 13F, 0F));

		definition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

		head.addOrReplaceChild("right_ear", CubeListBuilder.create()
						.texOffs(48, 20)
						.addBox(0F, -4F, 0F, 4, 4, 1),
				PartPose.offsetAndRotation(3.5F, -3F, -1F, 0.0F, 0.2617994F, -0.3490659F));

		head.addOrReplaceChild("left_ear", CubeListBuilder.create()
						.texOffs(48, 25)
						.addBox(-4F, -4F, 0F, 4, 4, 1),
				PartPose.offsetAndRotation(-3.5F, -3F, -1F, 0.0F, -0.2617994F, 0.3490659F));

		head.addOrReplaceChild("snout", CubeListBuilder.create()
						.texOffs(28, 0)
						.addBox(-1.5F, -2F, -2F, 3, 2, 3),
				PartPose.offset(0F, -2F, -3F));

		head.addOrReplaceChild("jaw", CubeListBuilder.create()
						.texOffs(28, 5)
						.addBox(-1.5F, 0F, -2F, 3, 1, 3),
				PartPose.offsetAndRotation(0F, -2F, -3F, 0.20944F, 0.0F, 0.0F));

		definition.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(12, 19)
						.addBox(0F, 0F, 0F, 7, 7, 4),
				PartPose.offset(-3.5F, 12F, -2F));

		definition.addOrReplaceChild("right_arm", CubeListBuilder.create()
						.texOffs(36, 17)
						.addBox(-3F, -1F, -1.5F, 3, 7, 3),
				PartPose.offset(-3.5F, 12F, 0F));

		definition.addOrReplaceChild("left_arm", CubeListBuilder.create().mirror()
						.texOffs(36, 17)
						.addBox(0F, -1F, -1.5F, 3, 7, 3),
				PartPose.offset(3.5F, 12F, 0F));

		definition.addOrReplaceChild("right_leg", CubeListBuilder.create()
						.texOffs(0, 20)
						.addBox(-1.5F, 0F, -1.5F, 3, 5, 3),
				PartPose.offset(-2F, 19F, 0F));

		definition.addOrReplaceChild("left_leg", CubeListBuilder.create().mirror()
						.texOffs(0, 20)
						.addBox(-1.5F, 0F, -1.5F, 3, 5, 3),
				PartPose.offset(2F, 19F, 0F));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public void setupAnim(Kobold entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);

		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.rightArm.zRot = 0.0F;
		this.leftArm.zRot = 0.0F;

		this.rightArm.xRot = -((float) Math.PI * .15F);
		this.leftArm.xRot = -((float) Math.PI * .15F);

		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.rightLeg.yRot = 0.0F;
		this.leftLeg.yRot = 0.0F;

		this.rightArm.zRot += Mth.cos(ageInTicks * 0.19F) * 0.15F + 0.05F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.19F) * 0.15F + 0.05F;
		this.rightArm.xRot += Mth.sin(ageInTicks * 0.267F) * 0.25F;
		this.leftArm.xRot -= Mth.sin(ageInTicks * 0.267F) * 0.25F;

		if (this.isJumping) {
			// open jaw
			this.jaw.xRot = 1.44F;
		} else {
			this.jaw.xRot = 0.20944F;
		}
	}

	@Override
	public void prepareMobModel(Kobold entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		// check if entity is jumping
		this.isJumping = entity.getDeltaMovement().y() > 0;
	}
}
