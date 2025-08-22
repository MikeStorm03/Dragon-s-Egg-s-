package com.msg.platform;

public interface DragonsEggSPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    /**
     * Check if the mod is running on server side.
     * 
     * @return if the mod is running on server side.
     */
    boolean isServerSide();

    /**
     * Get the enviroment type the mod is running.
     * 
     * @return the name of enviroment type.
     */
    default String getEnvironmentType() {
        return isServerSide() ? "server" : "client";
    }
}