 package com.hbm.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.common.MinecraftForge;

import java.util.Iterator;
import java.util.Map;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.*;
import com.hbm.entity.effect.*;
import com.hbm.entity.grenade.*;
import com.hbm.entity.item.EntityMinecartTest;
import com.hbm.entity.logic.*;
import com.hbm.entity.missile.*;
import com.hbm.entity.mob.*;
import com.hbm.entity.particle.*;
import com.hbm.entity.projectile.*;
import com.hbm.items.ModItems;
import com.hbm.particle.ParticleContrail;
import com.hbm.particle.ParticleSmokePlume;
import com.hbm.render.block.*;
import com.hbm.render.entity.*;
import com.hbm.render.item.*;
import com.hbm.render.misc.MissilePart;
import com.hbm.render.tileentity.*;
import com.hbm.render.util.HmfModelLoader;
import com.hbm.sound.AudioWrapper;
import com.hbm.sound.AudioWrapperClient;
import com.hbm.tileentity.bomb.*;
import com.hbm.tileentity.conductor.*;
import com.hbm.tileentity.deco.*;
import com.hbm.tileentity.machine.*;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
	@Override
	public void registerRenderInfo()
	{
		MinecraftForge.EVENT_BUS.register(new ModEventHandlerClient());

		AdvancedModelLoader.registerModelHandler(new HmfModelLoader());

		RenderingRegistry.registerBlockHandler(new RenderTaintBlock());
		RenderingRegistry.registerBlockHandler(new RenderScaffoldBlock());
		RenderingRegistry.registerBlockHandler(new RenderTapeBlock());
		RenderingRegistry.registerBlockHandler(new RenderSteelBeam());
		RenderingRegistry.registerBlockHandler(new RenderBarrel());

		MinecraftForgeClient.registerItemRenderer(ModItems.assembly_template, new ItemRenderTemplate());
		MinecraftForgeClient.registerItemRenderer(ModItems.chemistry_template, new ItemRenderTemplate());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTestRender.class, new RenderTestRender());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTestContainer.class, new RenderTestContainer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.test_container), new ItemRenderTestContainer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTestBombAdvanced.class, new RenderTestBombAdvanced());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.test_bomb_advanced), new ItemRenderTestBombAdvanced());
		
		MinecraftForgeClient.registerItemRenderer(ModItems.redstone_sword, new ItemRenderRedstoneSword());
		MinecraftForgeClient.registerItemRenderer(ModItems.big_sword, new ItemRenderBigSword());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRotationTester.class, new RenderRotationTester());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObjTester.class, new RendererObjTester());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeGadget.class, new RenderNukeGadget());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.nuke_gadget), new ItemRenderNukeGadget());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeBoy.class, new RenderNukeBoy());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeCustom.class, new RenderNukeCustom());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeSolinium.class, new RenderNukeSolinium());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeN2.class, new RenderNukeN2());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeMan.class, new RenderNukeMan());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeN45.class, new RenderNukeN45());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretHeavy.class, new RenderHeavyTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretRocket.class, new RenderRocketTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretLight.class, new RenderLightTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretFlamer.class, new RenderFlamerTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretTau.class, new RenderTauTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretSpitfire.class, new RenderSpitfireTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretCIWS.class, new RenderCIWSTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretCheapo.class, new RenderCheapoTurret());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLandmine.class, new RenderLandmine());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCelPrime.class, new RenderCelPrimeTower());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCelPrimeTerminal.class, new RenderCelPrimePart());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCelPrimeBattery.class, new RenderCelPrimePart());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCelPrimePort.class, new RenderCelPrimePart());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCelPrimeTanks.class, new RenderCelPrimePart());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFF.class, new RenderForceField());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityForceField.class, new RenderMachineForceField());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineCentrifuge.class, new RenderCentrifuge());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineGasCent.class, new RenderCentrifuge());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineUF6Tank.class, new RenderUF6Tank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachinePuF6Tank.class, new RenderPuF6Tank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineIGenerator.class, new RenderIGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineCyclotron.class, new RenderCyclotron());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineOilWell.class, new RenderDerrick());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineGasFlare.class, new RenderGasFlare());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineMiningDrill.class, new RenderMiningDrill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineAssembler.class, new RenderAssembler());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineChemplant.class, new RenderChemplant());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineFluidTank.class, new RenderFluidTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineRefinery.class, new RenderRefinery());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachinePumpjack.class, new RenderPumpjack());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineTurbofan.class, new RenderTurbofan());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachinePress.class, new RenderPress());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineEPress.class, new RenderEPress());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineRadGen.class, new RenderRadGen());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineRadar.class, new RenderRadar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineSeleniumEngine.class, new RenderSelenium());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineReactorSmall.class, new RenderSmallReactor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineShredderLarge.class, new RenderMachineShredder());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVaultDoor.class, new RenderVaultDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlastDoor.class, new RenderBlastDoor());

		//RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderSnowball(ModItems.man_core));

		MinecraftForgeClient.registerItemRenderer(ModItems.gun_rpg, new ItemRenderRpg());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_karl, new ItemRenderRpg());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_panzerschreck, new ItemRenderRpg());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_stinger, new ItemRenderStinger());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_skystinger, new ItemRenderStinger());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_hk69, new ItemRenderWeaponObj());
		//MinecraftForgeClient.registerItemRenderer(ModItems.gun_rpg_ammo, new ItemRenderRocket());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBombMulti.class, new RenderBombMulti());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeMike.class, new RenderNukeMike());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeTsar.class, new RenderNukeTsar());

		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeGeneric.class, new RenderSnowball(ModItems.grenade_generic));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeStrong.class, new RenderSnowball(ModItems.grenade_strong));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeFrag.class, new RenderSnowball(ModItems.grenade_frag));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeFire.class, new RenderSnowball(ModItems.grenade_fire));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeCluster.class, new RenderSnowball(ModItems.grenade_cluster));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeFlare.class, new RenderFlare());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeElectric.class, new RenderSnowball(ModItems.grenade_electric));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadePoison.class, new RenderSnowball(ModItems.grenade_poison));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeGas.class, new RenderSnowball(ModItems.grenade_gas));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeSchrabidium.class, new RenderSnowball(ModItems.grenade_schrabidium));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeNuke.class, new RenderSnowball(ModItems.grenade_nuke));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeNuclear.class, new RenderSnowball(ModItems.grenade_nuclear));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadePlasma.class, new RenderSnowball(ModItems.grenade_plasma));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeTau.class, new RenderSnowball(ModItems.grenade_tau));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeLemon.class, new RenderSnowball(ModItems.grenade_lemon));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeMk2.class, new RenderSnowball(ModItems.grenade_mk2));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeZOMG.class, new RenderSnowball(ModItems.grenade_zomg));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeASchrab.class, new RenderSnowball(ModItems.grenade_aschrab));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadePulse.class, new RenderSnowball(ModItems.grenade_pulse));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeShrapnel.class, new RenderSnowball(ModItems.grenade_shrapnel));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeBlackHole.class, new RenderSnowball(ModItems.grenade_black_hole));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeGascan.class, new RenderSnowball(ModItems.grenade_gascan));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeCloud.class, new RenderSnowball(ModItems.grenade_cloud));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadePC.class, new RenderSnowball(ModItems.grenade_pink_cloud));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeSmart.class, new RenderSnowball(ModItems.grenade_smart));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeMIRV.class, new RenderSnowball(ModItems.grenade_mirv));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeBreach.class, new RenderSnowball(ModItems.grenade_breach));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeBurst.class, new RenderSnowball(ModItems.grenade_burst));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFGeneric.class, new RenderSnowball(ModItems.grenade_if_generic));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFHE.class, new RenderSnowball(ModItems.grenade_if_he));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFBouncy.class, new RenderSnowball(ModItems.grenade_if_bouncy));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFSticky.class, new RenderSnowball(ModItems.grenade_if_sticky));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFImpact.class, new RenderSnowball(ModItems.grenade_if_impact));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFIncendiary.class, new RenderSnowball(ModItems.grenade_if_incendiary));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFToxic.class, new RenderSnowball(ModItems.grenade_if_toxic));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFConcussion.class, new RenderSnowball(ModItems.grenade_if_concussion));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFBrimstone.class, new RenderSnowball(ModItems.grenade_if_brimstone));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFMystery.class, new RenderSnowball(ModItems.grenade_if_mystery));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFSpark.class, new RenderSnowball(ModItems.grenade_if_spark));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFHopwire.class, new RenderSnowball(ModItems.grenade_if_hopwire));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeIFNull.class, new RenderSnowball(ModItems.grenade_if_null));

		RenderingRegistry.registerEntityRenderingHandler(EntitySchrab.class, new RenderFlare());

	    RenderingRegistry.registerEntityRenderingHandler(EntityTestMissile.class, new RenderTestMissile());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeFleija.class, new RenderNukeFleija());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrashedBomb.class, new RenderCrashedBomb());
		
	    RenderingRegistry.registerEntityRenderingHandler(EntityNukeCloudSmall.class, new RenderSmallNukeMK3());
	    RenderingRegistry.registerEntityRenderingHandler(EntityNukeCloudBig.class, new RenderBigNuke());
	    RenderingRegistry.registerEntityRenderingHandler(EntityCloudFleija.class, new RenderCloudFleija());
	    RenderingRegistry.registerEntityRenderingHandler(EntityCloudFleijaRainbow.class, new RenderCloudRainbow());
	    RenderingRegistry.registerEntityRenderingHandler(EntityCloudSolinium.class, new RenderCloudSolinium());
	    RenderingRegistry.registerEntityRenderingHandler(EntityNukeCloudNoShroom.class, new RenderNoCloud());
	    RenderingRegistry.registerEntityRenderingHandler(EntityFalloutRain.class, new RenderFallout());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, new RenderBlackHole());
	    RenderingRegistry.registerEntityRenderingHandler(EntityVortex.class, new RenderBlackHole());
	    RenderingRegistry.registerEntityRenderingHandler(EntityRagingVortex.class, new RenderBlackHole());
	    RenderingRegistry.registerEntityRenderingHandler(EntityDeathBlast.class, new RenderDeathBlast());

		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoTapeRecorder.class, new RenderTapeRecorder());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoSteelPoles.class, new RenderSteelPoles());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoPoleTop.class, new RenderPoleTop());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoPoleSatelliteReceiver.class, new RenderPoleSatelliteReceiver());
		
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.tape_recorder), new ItemRenderTapeRecorder());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steel_poles), new ItemRenderSteelPoles());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.pole_top), new ItemRenderPoleTop());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.pole_satellite_receiver), new ItemRenderSatelliteReceiver());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTaint.class, new RenderTaint());

		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver, new ItemRenderRevolver());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_saturnite, new ItemRenderRevolver());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_iron, new ItemRenderRevolverIron());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_gold, new ItemRenderRevolverGold());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_lead, new ItemRenderRevolverLead());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_schrabidium, new ItemRenderRevolverSchrabidium());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_cursed, new ItemRenderRevolverCursed());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_nightmare, new ItemRenderRevolverNightmare(ModItems.gun_revolver_nightmare));
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_nightmare2, new ItemRenderRevolverNightmare(ModItems.gun_revolver_nightmare2));
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_fatman, new ItemRenderFatMan());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_proto, new ItemRenderFatMan());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_mirv, new ItemRenderMIRVLauncher());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_bf, new ItemRenderBFLauncher());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_xvl1456, new ItemRenderXVL1456());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_zomg, new ItemRenderZOMG());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_osipr, new ItemRenderOSIPR());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_mp, new ItemRenderMP());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_inverted, new ItemRenderRevolverInverted());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_mp40, new ItemRenderMP40());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_emp, new ItemRenderEMPRay());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_immolator, new ItemRenderImmolator());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_cryolator, new ItemRenderCryolator());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_uboinik, new ItemRenderUboinik());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_jack, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_spark, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_hp, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_euthanasia, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_defabricator, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_dash, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_twigun, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_pip, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_nopip, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_blackjack, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_revolver_red, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_dampfmaschine, new ItemRenderBullshit());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_lever_action, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_bolt_action, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_lever_action_dark, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_bolt_action_green, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_lever_action_sonata, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_bolt_action_saturnite, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_b92, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_b93, new ItemRenderGunAnim());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_uzi, new ItemRenderUZI());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_uzi_silencer, new ItemRenderUZI());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_uzi_saturnite, new ItemRenderUZI());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_uzi_saturnite_silencer, new ItemRenderUZI());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_calamity, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_calamity_dual, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_minigun, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_avenger, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_lacunae, new ItemRenderOverkill());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_folly, new ItemRenderOverkill());

		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_dig, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_silk, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_ext, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_miner, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_hit, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_beam, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_sky, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_mega, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_joule, new ItemRenderMultitool());
		MinecraftForgeClient.registerItemRenderer(ModItems.multitool_decon, new ItemRenderMultitool());

		MinecraftForgeClient.registerItemRenderer(ModItems.shimmer_sledge, new ItemRenderShim());
		MinecraftForgeClient.registerItemRenderer(ModItems.shimmer_axe, new ItemRenderShim());
		
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_brimstone, new ItemRenderObj());

		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderRocket());
		RenderingRegistry.registerEntityRenderingHandler(EntityBulletBase.class, new RenderBullet());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMiniNuke.class, new RenderMiniNuke());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMiniMIRV.class, new RenderMiniMIRV());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBaleflare.class, new RenderBaleflare());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_fatman_ammo, new ItemRenderMiniNuke());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_mirv_ammo, new ItemRenderMIRV());
		MinecraftForgeClient.registerItemRenderer(ModItems.gun_bf_ammo, new ItemRenderBaleflare());
		RenderingRegistry.registerEntityRenderingHandler(EntityRainbow.class, new RenderRainbow());
		RenderingRegistry.registerEntityRenderingHandler(EntityNightmareBlast.class, new RenderOminousBullet());
		RenderingRegistry.registerEntityRenderingHandler(EntityFire.class, new RenderFireball(ModItems.energy_ball));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasmaBeam.class, new RenderBeam());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserBeam.class, new RenderBeam2());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinerBeam.class, new RenderBeam3());
		RenderingRegistry.registerEntityRenderingHandler(EntitySparkBeam.class, new RenderBeam4());
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveBeam.class, new RenderBeam5());
		RenderingRegistry.registerEntityRenderingHandler(EntityModBeam.class, new RenderBeam6());
		RenderingRegistry.registerEntityRenderingHandler(EntityLN2.class, new RenderLN2(ModItems.energy_ball));
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityNukeExplosionAdvanced.class, new RenderSnowball(ModItems.energy_ball));

		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartTest.class, new RenderMinecartTest());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukePrototype.class, new RenderNukePrototype());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaunchPad.class, new RenderLaunchPadTier1());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineMissileAssembly.class, new RenderMissileAssembly());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCompactLauncher.class, new RenderCompactLauncher());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaunchTable.class, new RenderLaunchTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoyuzLauncher.class, new RenderSoyuzLauncher());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOilDuct.class, new RenderOilDuct());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGasDuct.class, new RenderGasDuct());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidDuct.class, new RenderFluidDuct());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRFDuct.class, new RenderRFCable());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPylonRedWire.class, new RenderPylon());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStructureMarker.class, new RenderStructureMaker());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiblock.class, new RenderMultiblock());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAMSBase.class, new RenderAMSBase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAMSEmitter.class, new RenderAMSEmitter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAMSLimiter.class, new RenderAMSLimiter());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoreEmitter.class, new RenderCoreComponent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoreReceiver.class, new RenderCoreComponent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoreInjector.class, new RenderCoreComponent());

	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileCustom.class, new RenderMissileCustom());
	    
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileGeneric.class, new RenderMissileGeneric());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileAntiBallistic.class, new RenderMissileGeneric());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileIncendiary.class, new RenderMissileGeneric());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileCluster.class, new RenderMissileGeneric());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileBunkerBuster.class, new RenderMissileGeneric());

	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileStrong.class, new RenderMissileStrong());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileIncendiaryStrong.class, new RenderMissileStrong());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileClusterStrong.class, new RenderMissileStrong());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileBusterStrong.class, new RenderMissileStrong());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileEMPStrong.class, new RenderMissileStrong());

	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileBurst.class, new RenderMissileHuge());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileInferno.class, new RenderMissileHuge());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileRain.class, new RenderMissileHuge());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileDrill.class, new RenderMissileHuge());

	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileNuclear.class, new RenderMissileNuclear());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileMirv.class, new RenderMissileMirv());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMIRV.class, new RenderMirv());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileDoomsday.class, new RenderMissileDoomsday());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBombletTheta.class, new RenderBombletTheta());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBombletZeta.class, new RenderBombletTheta());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBombletSelena.class, new RenderBombletSelena());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, new RenderMeteor());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBoxcar.class, new RenderBoxcar());
	    RenderingRegistry.registerEntityRenderingHandler(EntityDuchessGambit.class, new RenderBoxcar());
	    RenderingRegistry.registerEntityRenderingHandler(EntityCarrier.class, new RenderCarrierMissile());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBooster.class, new RenderBoosterMissile());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBomber.class, new RenderBomber());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBurningFOEQ.class, new RenderFOEQ());
	    RenderingRegistry.registerEntityRenderingHandler(EntityFallingNuke.class, new RenderFallingNuke());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMinerRocket.class, new RenderMinerRocket());
	    RenderingRegistry.registerEntityRenderingHandler(EntityBobmazon.class, new RenderMinerRocket());
	    RenderingRegistry.registerEntityRenderingHandler(EntityTom.class, new RenderTom());
	    
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileTaint.class, new RenderMissileTaint());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileMicro.class, new RenderMissileTaint());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileBHole.class, new RenderMissileTaint());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileSchrabidium.class, new RenderMissileTaint());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileEMP.class, new RenderMissileTaint());

	    RenderingRegistry.registerEntityRenderingHandler(EntityAAShell.class, new RenderMirv());
	    RenderingRegistry.registerEntityRenderingHandler(EntityRocketHoming.class, new RenderSRocket());

	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileEndo.class, new RenderMissileThermo());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMissileExo.class, new RenderMissileThermo());
	    
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlock.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBroadcaster.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGeiger.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadioRec.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadiobox.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBomber.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachineSatDock.class, new RenderDecoBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlockAlt.class, new RenderDecoBlockAlt());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlockAltG.class, new RenderDecoBlockAlt());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlockAltW.class, new RenderDecoBlockAlt());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlockAltF.class, new RenderDecoBlockAlt());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steel_wall), new ItemRenderDecoBlock());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steel_corner), new ItemRenderDecoBlock());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steel_roof), new ItemRenderDecoBlock());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steel_beam), new ItemRenderDecoBlock());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steel_scaffold), new ItemRenderDecoBlock());

	    RenderingRegistry.registerEntityRenderingHandler(EntityNuclearCreeper.class, new RenderNuclearCreeper());
	    RenderingRegistry.registerEntityRenderingHandler(EntityTaintedCreeper.class, new RenderTaintedCreeper());
	    RenderingRegistry.registerEntityRenderingHandler(EntityHunterChopper.class, new RenderHunterChopper());
	    RenderingRegistry.registerEntityRenderingHandler(EntityCyberCrab.class, new RenderCyberCrab());

	    RenderingRegistry.registerEntityRenderingHandler(EntityChopperMine.class, new RenderChopperMine());
	    RenderingRegistry.registerEntityRenderingHandler(EntityRubble.class, new RenderRubble());
	    RenderingRegistry.registerEntityRenderingHandler(EntityShrapnel.class, new RenderShrapnel());
	    RenderingRegistry.registerEntityRenderingHandler(EntityOilSpill.class, new RenderEmpty());
	    RenderingRegistry.registerEntityRenderingHandler(EntityWaterSplash.class, new RenderEmpty());
	    RenderingRegistry.registerEntityRenderingHandler(EntityEMP.class, new RenderEmpty());

	    RenderingRegistry.registerEntityRenderingHandler(EntitySmokeFX.class, new MultiCloudRenderer(new Item[] { ModItems.smoke1, ModItems.smoke2, ModItems.smoke3, ModItems.smoke4, ModItems.smoke5, ModItems.smoke6, ModItems.smoke7, ModItems.smoke8 }));
	    RenderingRegistry.registerEntityRenderingHandler(EntityBSmokeFX.class, new MultiCloudRenderer(new Item[] { ModItems.b_smoke1, ModItems.b_smoke2, ModItems.b_smoke3, ModItems.b_smoke4, ModItems.b_smoke5, ModItems.b_smoke6, ModItems.b_smoke7, ModItems.b_smoke8 }));
	    RenderingRegistry.registerEntityRenderingHandler(EntityDSmokeFX.class, new MultiCloudRenderer(new Item[] { ModItems.d_smoke1, ModItems.d_smoke2, ModItems.d_smoke3, ModItems.d_smoke4, ModItems.d_smoke5, ModItems.d_smoke6, ModItems.d_smoke7, ModItems.d_smoke8 }));
	    RenderingRegistry.registerEntityRenderingHandler(EntityChlorineFX.class, new MultiCloudRenderer(new Item[] { ModItems.chlorine1, ModItems.chlorine2, ModItems.chlorine3, ModItems.chlorine4, ModItems.chlorine5, ModItems.chlorine6, ModItems.chlorine7, ModItems.chlorine8 }));
	    RenderingRegistry.registerEntityRenderingHandler(EntityPinkCloudFX.class, new MultiCloudRenderer(new Item[] { ModItems.pc1, ModItems.pc2, ModItems.pc3, ModItems.pc4, ModItems.pc5, ModItems.pc6, ModItems.pc7, ModItems.pc8 }));
	    RenderingRegistry.registerEntityRenderingHandler(com.hbm.entity.particle.EntityCloudFX.class, new MultiCloudRenderer(new Item[] { ModItems.cloud1, ModItems.cloud2, ModItems.cloud3, ModItems.cloud4, ModItems.cloud5, ModItems.cloud6, ModItems.cloud7, ModItems.cloud8 }));
	    RenderingRegistry.registerEntityRenderingHandler(EntityOrangeFX.class, new MultiCloudRenderer(new Item[] { ModItems.orange1, ModItems.orange2, ModItems.orange3, ModItems.orange4, ModItems.orange5, ModItems.orange6, ModItems.orange7, ModItems.orange8 }));
	    RenderingRegistry.registerEntityRenderingHandler(EntityFogFX.class, new FogRenderer());
	    RenderingRegistry.registerEntityRenderingHandler(EntitySSmokeFX.class, new SSmokeRenderer(ModItems.nuclear_waste));
	    RenderingRegistry.registerEntityRenderingHandler(EntityOilSpillFX.class, new SpillRenderer(ModItems.nuclear_waste));
	    RenderingRegistry.registerEntityRenderingHandler(EntityGasFX.class, new GasRenderer(ModItems.nuclear_waste));
	    RenderingRegistry.registerEntityRenderingHandler(EntityGasFlameFX.class, new GasFlameRenderer(ModItems.nuclear_waste));
	    RenderingRegistry.registerEntityRenderingHandler(EntityCombineBall.class, new RenderSnowball(ModItems.energy_ball));
	    RenderingRegistry.registerEntityRenderingHandler(EntityDischarge.class, new ElectricityRenderer(ModItems.discharge));
	    RenderingRegistry.registerEntityRenderingHandler(EntityEMPBlast.class, new RenderEMPBlast());
	    RenderingRegistry.registerEntityRenderingHandler(EntityTSmokeFX.class, new TSmokeRenderer(ModItems.nuclear_waste));
	    
		RenderingRegistry.addNewArmourRendererPrefix("5");
		RenderingRegistry.addNewArmourRendererPrefix("6");
		RenderingRegistry.addNewArmourRendererPrefix("7");
		RenderingRegistry.addNewArmourRendererPrefix("8");
		RenderingRegistry.addNewArmourRendererPrefix("9");
	}
	
	@Override
	public void registerMissileItems() {
		
		MissilePart.registerAllParts();
		
		Iterator it = MissilePart.parts.entrySet().iterator();
		
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        //System.out.println(pair.getKey() + " = " + pair.getValue());
	        MissilePart part = (MissilePart)pair.getValue();
			MinecraftForgeClient.registerItemRenderer(part.part, new ItemRenderMissilePart(part));
	    }
		
		MinecraftForgeClient.registerItemRenderer(ModItems.missile_custom, new ItemRenderMissile());
	}
	
	@Override
	public void registerTileEntitySpecialRenderer() {
		
	}
	
	@Override
	public void particleControl(double x, double y, double z, int type) {

		
		World world = Minecraft.getMinecraft().theWorld;
		TextureManager man = Minecraft.getMinecraft().renderEngine;
		
		switch(type) {
		case 0:
			
			for(int i = 0; i < 10; i++) {
				EntityCloudFX smoke = new EntityCloudFX(world, x + world.rand.nextGaussian(), y + world.rand.nextGaussian(), z + world.rand.nextGaussian(), 0.0, 0.0, 0.0);
				Minecraft.getMinecraft().effectRenderer.addEffect(smoke);
			}
			break;
			
		case 1:
			
			EntityCloudFX smoke = new EntityCloudFX(world, x, y, z, 0.0, 0.1, 0.0);
			Minecraft.getMinecraft().effectRenderer.addEffect(smoke);
			break;
			
		case 2:
			
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
			break;
		}
	}
	
	//version 2, now with strings!
	@Override
	public void spawnParticle(double x, double y, double z, String type, float args[]) {

		
		World world = Minecraft.getMinecraft().theWorld;
		TextureManager man = Minecraft.getMinecraft().renderEngine;
		
		if("launchsmoke".equals(type)) {
			ParticleSmokePlume contrail = new ParticleSmokePlume(man, world, x, y, z);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exKerosene".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exSolid".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z, 0.3F, 0.2F, 0.05F, 1F);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exHydrogen".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z, 0.7F, 0.7F, 0.7F, 1F);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
		if("exBalefire".equals(type)) {
			ParticleContrail contrail = new ParticleContrail(man, world, x, y, z, 0.2F, 0.7F, 0.2F, 1F);
			Minecraft.getMinecraft().effectRenderer.addEffect(contrail);
		}
	}
	
	@Override
	public AudioWrapper getLoopedSound(String sound, float x, float y, float z, float volume, float pitch) {
		
		AudioWrapperClient audio = new AudioWrapperClient(new ResourceLocation(sound));
		audio.updatePosition(x, y, z);
		return audio;
	}
}

