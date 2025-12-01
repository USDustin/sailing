package com.duckblade.osrs.sailing.debugplugin.module;

import com.duckblade.osrs.sailing.debugplugin.SailingDebugConfig;
import com.duckblade.osrs.sailing.debugplugin.features.BoatInfoOverlay;
import com.duckblade.osrs.sailing.debugplugin.features.CourierTaskOverlayPanel;
import com.duckblade.osrs.sailing.debugplugin.features.FacilitiesOverlay;
import com.duckblade.osrs.sailing.debugplugin.features.LocalBoatInfoOverlayPanel;
import com.duckblade.osrs.sailing.debugplugin.features.TlwpOverlay;
import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;

@Slf4j
public class DebugModule extends AbstractModule
{

	@Override
	protected void configure()
	{
		bind(DebugComponentManager.class);
	}

	@Provides
	Set<DebugLifecycleComponent> lifecycleComponents(
		BoatInfoOverlay boatInfoOverlay,
		CourierTaskOverlayPanel courierTaskOverlayPanel,
		FacilitiesOverlay facilitiesOverlay,
		LocalBoatInfoOverlayPanel localBoatInfoOverlayPanel,
		TlwpOverlay tlwpOverlay
	) throws IOException
	{
		var builder = ImmutableSet.<DebugLifecycleComponent>builder()
			.add(boatInfoOverlay)
			.add(courierTaskOverlayPanel)
			.add(facilitiesOverlay)
			.add(localBoatInfoOverlayPanel)
			.add(tlwpOverlay);

		return builder.build();
	}

	@Provides
	@Singleton
	SailingDebugConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SailingDebugConfig.class);
	}

}
