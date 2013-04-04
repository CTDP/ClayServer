# ClayServer

This is part of the Clay-Model-Viewer developed by Daniel 'Dahie' Senff for [CTDP][ctdp].
This system aims to allow easy cross-platform preview of 3D models in your browser.
In order to easily refresh and replace textures for preview, the website running
the 3D-viewer is hosted locally. 

ClayServer is a compact local HTTP-server, that hosts this 3D-viewer. 
It opens up a small HTTP-server on a predefined port and hosts a website included in the jar.
This website can be accessed via your browser or by any device in your local network.
Closing the app, closes the server without remains.

The ClayServer sources here do not include the actualy model viewer. This will be
made available somewhere else. 

## Project structure

### Document Root: `htdocs/`

Document root of the webserver. Everything in here is available on the server and 
will be included in the deployed jar.

### Resources: `res/`

Resources for the application itself, such as texts, configuration and images.

### Source Code: `src/`

Source code of the application.

## Contributing to ClayServer
 
* Check out the latest master to make sure the feature hasn't been implemented or the bug hasn't been fixed yet
* Check out the issue tracker to make sure someone already hasn't requested it and/or contributed it
* Fork the project
* Start a feature/bugfix branch
* Commit and push until you are happy with your contribution, then send a pull-request
* Please try not to mess with the build, version, or history. If you want to have your own version, or is otherwise necessary, that is fine, but please isolate to its own commit so I can cherry-pick around it.


## Links
* [Code Repository][github]
* [Bug Tracker][bugtracker]

## License

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.  
[GNU General Public License v2][license].


[license]: https://github.com/CTDP/ClayServer/tree/master/LICENSE.md
[github]: https://github.com/CTDP
[bugtracker]: https://github.com/CTDP/ClayServer/issues