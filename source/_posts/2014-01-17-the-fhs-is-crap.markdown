---
layout: post
title: "The FHS is crap"
date: 2014-01-17 17:10
comments: true
categories: rant,linux,standards,file system
---

*I found today this note I forgot about from October 2010, the format was clearly thought for this blog, but nowadays I wouldn't use such strong words as "crap". Anyhow since I still agree with it, here it is*


The Filesystem Hierarchy Standard is a mess, I never liked it, and nobody follows it in its entirety...
(Sorry for being so direct, but I thought about it, and I think it's the better way to start to explain what I think about this).

On the other hand, obviously, a bad standard is better than no standard at all...

I think that for the most part the distinction between /bin /sbin /opt /usr/bin & /usr/sbin is moot, and only forces us to use more environment variables, and makes more complicated to browse the system directories (and is especially a pain for all those users that don't know the existance of 'which').

Why kill is in /bin and killall in /usr/bin? why alsactl is in /sbin and dd is in /bin? why are bash and plymouth in /bin?

Sure, a posteriori maybe you could find some explanation, but when thinking about it there's no clear distinction between a binary and a system binary (sure, fdisk or init, fit very well into the definition of system binary... but is this reason enough for keeping the directories splitted? I don't think so...)

I also find useless the distinction of user commands in /usr/bin, when the fhs was first conceived, or on certain server installs, it could make sense...
But nowadays, when we find a 30GB SSD "paltry", and partitioning the disk of a desktop system between / and /home is (correctly) seen as futile, it doesn't.

"Nobody follows it in its entirety": The fhs says that python, perl, and similar things should reside in /usr/bin, but nowadays in the starting shebang of your scripts it's recommended to not use the full path for the interpreter, but instead to rely on /usr/bin/env
(Ok, it's not really a problem of standard, it depends on user choices, etc... But that's the point: if you can't rely on the standard and you don't get many benefits from it, why forcing the developers/packagers/distributions to put the interpreters separate directory from /bin in the first place? )

"Nobody follows it in its entirety": There's not only google chrome who installs itself into /opt, on my system I also have into /opt: the google talk plugin, world of goo, and mendeley...
But guess how many of these programs use /etc/opt/ and /var/opt/ ?
Zero...

I have 4 programs inside my /opt, and yet I'm above the average concerning this... I'd say that if something specified in a standard is so underutilized, it should be changed the next time that standard is revised...

Besides that, even if I can appreciate the flexibility conceded to the developers by putting their programs in /opt, I'd rather prefer that (since, at the end of the day, they are all shipped in .deb packages and are kept updated via apt just like all the other software in the system) their respective files would be in /bin /etc and using the system libraries: one of the reason that these programs are sitted in /opt is that they are shipping a their own copy of some system libraries (ffmpeg & sqlite, sdl & vorbis, qt4...)
If it's surely better for a videogame like world of goo, I find it not-so optimal for an heavily developed and open-source browser like google chrome.

And what about using it for programs like eclipse?
When I had to install that huge thing downloaded directly from the website (cause the repositories were stuck at 3.2), I never liked to put it into /opt and messing around using sudo and tweaking privileges...

In fact I always put it into a folder in my $HOME (tipically ~/Applications), because the only real advantage is for other users in being able to use that program...
But if the original developer didn't bother to create a package to help multiple users using it on the same computer, then I put the blame on them, and surely don't bother myself either into fixing this (and I obviously despise at the same way developers that ships software for windows only in standalone .exe format, without any .msi installer whatsoever).

But on the other hand, modern desktop systems (especially if mobile like a laptop/tablet)  have only one main users... And even when you have other users... How many of you have a brother/parent/spouse that needs to use eclipse just like you? (business settings obviously are another matter: and in fact, they despise software not shipped in a packaged form).

I think that overall, the situation wouldn't be worse if we merged (via symlinks or unionfs or whatever) /bin /sbin /usr/bin /usr/sbin /usr/local/bin /usr/local/sbin (except /opt, you may want to keep it for its flexibility, but otoh, as I wrote before, I don't think it has many real use cases) all together into /bin

Moving from that to the current state in the users' home:

I like the freedesktop base directory specification, and I wish it was followed universally... But there's one thing that sets me off: while the configuration files and the caches are kept under ~/.config and ~/.cache respectively; all the other data files are kept under ~/.local/share
dot-local-slash-share ?
I can only guess that originally it was supposed to be used as a per-user /usr directory, so we could have used it to store a .local/lib .local/bin .local/games .local/include .local/src .local/man and .local/etc (???)

...and yet now we have this .local folder whos only job is to keep a share/ folder.

What about the Music, Images, etc. folder instead? No standards whatsoever!
If I'm not wrong shuttlework himself wrote in a blog post about how, to help with some new unity interface features, we could use some environmental variables to point to the correct localized folders inside $HOME, but afaik we're still here with no easy way to find the correct folder automatically.

Here... I'm done... I think that, as of today, the standards can be much improved; but obviously, these are only my 2 cents (even though I strongly believe in them).
