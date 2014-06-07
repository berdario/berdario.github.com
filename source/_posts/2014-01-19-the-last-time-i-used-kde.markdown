---
layout: post
title: "The last time I used KDE"
date: 2014-01-19 23:55
comments: true
categories: [rant, linux, desktop]
---

*It happened again: I postponed publishing new posts for months (it's now June when I'm publishing this), and I built up a small backlog of things I want to write about. This time it's about KDE: I gathered some of my opinions back in the October of 2011, and after February I got to use it again at work (the workstation ran Nixos and later Fedora, and in both cases KDE seemed like a better choice than modern Gnome), maybe that was the reason for me to postpone this post, anyhow... on with the impressions that I got in 2011:*

All of the following is based on Opensuse: it was the very first Linux distribution I ever saw, but after years of Ubuntu I was curious on the current state of Opensuse+KDE.

The first problem was the lack of some packages, but I felt more that the defaults were bothering me more: apparently almost no-one I knew was satisfied with them, and I was disappointed to see that the keyboard shortcuts for very common functions like switching from a workspace to the next, weren't set.

Another issue was the messiness of the desktop, resulting from some of the KDE apps chosing to open new windows instead of opening things in new tabs in the existing windows, like Kopete and Kwrite.

At the time I tried KDE 4.6, which at first glance was better than the 4.4 I tried even earlier: I disliked interacting with Widgets and Activities, but most of all the segfaults in Dolphin and Krunner. KDE 4.6 on comparison was much more stable. The problem with widgets was that I seldom use things that are stuck on desktop, if I have them enabled it's because they supply me some useful information, and for that reason I'd like to have them always available on the panel, but the reduced look & feel on the panel was horrible.

Kontact was apparently even slower than Evolution when fetching mail. It required Akonadi, but the weird thing is that by default it wasn't starting up Nepomuk (which was necessary to actually for it to work). The integration with Google in Kontact was also incomplete.

Another issue is the default delay for hot corners: 150ms makes it feel sluggish. Choqok didn't support Facebook and Google Buzz (hey, I was one of its few users at the time :D ). Amarok 2.4's notifications popped up only 3 seconds after the start of the song, and it hid some useful automatic playlists in `Media sources -> Dynamic playlists -> Automated playlist generator`. Digikam lacked a single-key shortcut to the next photo.

Also (apparently it was supposed to be added in KDE 4.7 but I never tried it out) it was lacking a zoom functionality like the Compiz one, that is: gradual zooming by using the mouse scrollbar. The KDE zoom was only usable with keybindings (which obviously aren't as fluid to use ad quick flick of the scrollbar, if you need just a certain amount of zooming).

Dolphin wasn't hiding bookmarks to volumes that are not currently available (unlike an old version of Gnome's Nautilus, which unfortunately regressed since then -_- ).

In a lot of the popups the words are cut off: you have to resize the window or scroll laterally. When you log in, Kwallet isn't unlocked automatically (in Gnome you have to unlock the keyring manually only if you changed the password of one between the keyring or the user to be different than the other). Another cause of concern was the over-reliance on dbus: as I mentioned the system could be quite unstable with segfaults not totally uncommon: well, if dbus failed, the whole system was rendered almost unusable: no launcher, etc. (also other systems have the same weakness, like the global menu in Ubuntu's Unity) but I found it particularly aggravating in KDE.

The `.xmodmap` file inside your home is not recognized at login, and automatically loaded (another small feature that made my life with Gnome simpler). While it should be possible to do it manually by putting a script inside `.kde4/Autostart/` I always found the KDE autostart quite unreliable and hard to debug (Like, if the file is not executable or lacks a shebang, it will be opened for editing instead of logging the error).

Unfortunately, this post isn't very structured and I'd prefer to accompany each point with a screenshot, but at least this explains why KDE isn't my first choice for a Linux Desktop :)



