Tobacco
=======

Tobacco is a 2D (at the moment) OpenGL game engine written from scratch in Java (for portability).
It implements a very raw Entity-Component-System pattern. I chose that design because I find it to be very reusable and easy to hack in a clean and organized (OCD-friendly) way.

I'm slowly improving on it as I plan it to be the base for any future amateur games I make.

The design separates logic common to all games from rendering logic and game specific logic in three different layers so if I ever need to render an already written game on, say, Android, I can easily write a new renderer and reuse everything else. Neat, huh?
We'll see how it all works out!
