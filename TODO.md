# Extract Struct From Params
Plugin for Goland. Use it to extract long lists of function/method parameters into input Struct

![Build](https://github.com/guilhermearpassos/Goland-ExtractStructFromParamsPlugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/24704.svg)](https://plugins.jetbrains.com/plugin/24704)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/24704.svg)](https://plugins.jetbrains.com/plugin/24704)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [x] Get familiar with the [template documentation][template].
- [x] Adjust the [pluginGroup](./gradle.properties), [plugin ID](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [x] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [x] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [x] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [x] Set the `PLUGIN_ID` in the above README badges.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections. 
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "ExtractStructFromParams"</kbd> >
  <kbd>Install</kbd>
  
- Manually:

  Download the [latest release](https://github.com/guilhermearpassos/Goland-ExtractStructFromParamsPlugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

# Roadmap
## Features List
- [ ] Allow extracting struct definition from params
  - [x] dynamically create struct definition
  - [x] place struct definition in a consistent spot
  - [x] replace params with the struct
  - [x] replace parameter usages with struct fields
  - [x] find all calls to the func/method
  - [x] dynamically construct struct with caller params
  - [x] replace caller params
  - [x] deal with package references
  - [ ] add import statement when needed
- [ ] Allow extracting struct from return params
  - [ ] TBD...
  - [ ] named returns
- [ ] Allow customization of struct names and fields
  - [ ] TBD...
- [ ] Allow partial extractions
  - [ ] TBD...


[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
