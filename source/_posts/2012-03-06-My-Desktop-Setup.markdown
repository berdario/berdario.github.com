---
layout: post
title: "My Desktop Setup"
date: 2012-03-06 15:32
comments: true
categories: [my setup, linux]
---

It took me more time than I expected to write up this second post, I hope to be able to ramp up the rhythm soon.
The reason is that I haven't really wrote this up by impulse... In fact I wanted to write this since quite some time, and in the meanwhile in the last days, I was busy planning a [small Jam](http://bglug.it/2012/02/23/bglug-partecipa-allubuntu-global-jam) along with other small misc duties.

Two years and a half ago I bought a new desktop computer, it had to be shared with my family, so I planned that it would've run Windows alongside Linux.

That's not too bad, since having the opportunity to run games or other software natively on Windows is nice to have, but the truth is that being this only a secondary computer for me, it would've run Windows most of the time.

So, to test something that I hadn't tried yet at the time (that would've been useful in case someone dropped by our LUG asking for support about it) and to skip chainloading the Windows bootloader (thus potentially slowing down the boot, something I didn't want: Given that I used an ssd as a boot drive, I wanted to avoid such things, even if it would've only a marginal effect) I chose to use wubi.

For those that don't know about it: it avoids changing the bootloader with grub and installs ubuntu in a loopback mounted virtual disk (a normal file under windows). It was quite neat, but I stumbled upon some nasty issues when upgrading to grub2, such as [this bug](https://bugs.launchpad.net/ubuntu/+source/grub2/+bug/477104).

At the end, I'm not quite convinced of the tradeoff in complexity for new users, given the potential nasty edge cases...

Btw, I also decided to try some other things I didn't really have much experience in linux... One of these, if I remember correctly, was Ext4; while the other was simply installing Linux on an ATI gpu.

ATI has always had a bad reputation among linux users, but when I chose the hardware for that desktop, the Radeon 4890 seemed like a nice choice, and I was curious about proving that wrong.

In fact, it's been some years now that they started an [effort to supply a good OSS driver](http://www.phoronix.com/scan.php?page=article&item=826&num=1) for their cards. Sure, you can still be out of luck when running a bleeding edge distribution, due to the delay in updating the drivers to get them working with the latest changes in Xorg and such...

In fact Ubuntu had to [get into agreements with ATI](http://www.phoronix.com/scan.php?page=article&item=amd_open_jaunty&num=1) to be able to ship the drivers in time or [backport some of the Xorg changes](http://www.phoronix.com/scan.php?page=article&item=ubuntu_1104_radeon&num=1).

Surely, I still found Intel gpu drivers, to be more stable (even if lacking in OpenGL features and performance, and obviously I'm not counting the horrible GMA500), but I find it strange that Linux people are still preferring Nvidia over Ati, when they're being so hostile by [removing support to their old 2d-only open source driver](http://www.phoronix.com/scan.php?page=article&item=nvidia_kills_nv&num=1) altogheter without commiting to help the [Nouveau project](http://nouveau.freedesktop.org/).

I've also seen plenty of times, Linux users with an Nvidia powered laptop, struggling to set up correctly an external display/beamer, this due to the fact that Nvidia doesn't support xrandr1.2 and pushes for their own TwinView option, with their own configuration tools, and their own set of problems.

With this setup, I've been able to see some interesting things: like the performance of the loopback drive... by doing a readonly benchmark inside palimpsest I noticed that it was about half of the performance when directly accessing the ntfs drive (that, being a 30GB SSD, is thus really tight for hosting 2 OS installations)

I also chose to keep all the user configuration files on the SSD, and move only huge things on the secondary traditional hard drive... Thanks to the fact that both Linux and Windows Vista support symbolic links, this gives me quite a bit of flexibility in setting that up. That said, I don't think that the small advantage of being able to load chrome's sqlite dbs faster and such things outweighs the time lost cramming everything into such a small space and tracking down rogue programs/users when those filled their home folder (not to mention the pain of setting up all the symlink for each new user).

In fact I think I won't ever use such a directory layout... Heck: it's 2012, we aren't supposed to micromanage disk space this way anymore.

