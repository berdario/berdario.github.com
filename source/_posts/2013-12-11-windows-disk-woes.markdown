---
layout: post
title: "Windows disk woes"
date: 2013-12-11 12:16
comments: true
categories: [windows]
---

Another six months passed since the last post: if you look at [the sources](https://github.com/berdario/berdario.github.com/tree/source) you'll see that I've dabbled with a rewrite of the javascript sources to clojurescript, so due to this overhaul (that I haven't even completed) I postponed writing once again -_-

Anyhow, this is another small rant: as I wrote last time, the home's desktop runs Windows (8, nowadays) on a 30GB SSD... I have a second traditional HDD that stores games, user folders, and bigger programs. The SSD only stores the core system and some small and core programs, but this summer the situation turned out to be unbearable:

{% img /images/windisk/windisk1.png windirstat screenshot %}

(If you want to read easily the screenshot, just open it in a new tab)

The Windows folder started to require 23.5 GB all for its own (Yes, excluding all the things in Program Files), and the cleanup utility was unable to find anything more than 8MB worth of files to clean up.

So, by looking up manually I realized that old MSIs could've been [wasting some of that space](http://blogs.msdn.com/b/heaths/archive/2007/01/17/the-patch-cache-and-freeing-space.aspx) inside `C:\WINDOWS\Installer\$PatchCache$`

{% img /images/windisk/windisk2.png windirstat screenshot with PatchCache selected %}

As you can see, the footprint of my programs is quite lightweight (VS and the win SDK take up most of the space)

{% img /images/windisk/windisk4.png windirstat screenshot with ProgramFilesx86 selected %}

But inside the 64bit ProgramFiles, there was nasty surprise waiting:

A lot of space was being used up by Windows8 apps: not only you can't easily access their folder inside `%ProgramFiles%` without changing permissions...

{% img /images/windisk/windisk3.png windirstat screenshot with windows8 apps folder selected %}

But on top of that, the Uninstall Programs dialog completely ignores these apps footprint:

{% img /images/windisk/windisk5.png windirstat screenshot with uninstall program dialog %}

To make this even worse, if you look at the contents, you'll see that there's some duplication: XboxLIVEGames, at least 3 copies of ZuneMusic, etc.

Apparently Windows8 "helpfully" stores old versions of all your metro apps, doesn't let you see the disk footprint of these, doesn't let you change the installation folder and doesn't even give you an easy to access way to purge these old programs from the system.

(Things might have changed in the meanwhile, especially with the release of 8.1, but as you can guess, updating to 8.1 when Windows leaves so few disk space available is not really feasible: all Windows version upgrade usually cannot use a second disk as cache for the files to be installed, thus it requires at least twice the disk space that will be used for the installed system)

Oh, well... time to change that 30 GB SSD :/

(Just a small note, in case I or someone else might need to compress the palette used by screenshots like these: I tried out optipng, pngnq, pngquant and pngcrush comparing the size of the output files and pngnq seems to yield the best results -together with pngquant, if I remember correctly-)