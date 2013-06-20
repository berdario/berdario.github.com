---
layout: post
title: "Back to sanity"
date: 2013-06-17 22:15
comments: true
categories: [personal, this blog]
---

A lot of time passed since my last post... In fact it has been almost precisely a year since when I tried to write my 3rd blog post, but to my dismay I realized that the connection to the db failed.

So, it was June 2012 at the time... 3 months passed since the previous post, and I decided to start learning F#, I attended some courses at the university and passed 2 more exams (I wanted to use F# for the OpenGL course, but at the end I procrastinated too much).

Some other things happened as well, among them: the passing of my longest lived cat, Lady.

{% img right /images/lady.png 300 "my cat's obituary" %}

It was quite sad, she had to be put down after a tumor grow back after surgery (she went under the knives 2 times) and the vet thought that we should've spared her the last days: she was barely moving on the bed (but she was still willing to eat: I deluded myself into thinking that this meant she was still feeling somewhat alright).

It was the first time I witnessed the death of something I could relate to, but my reaction was totally silent and composed. I don't think of myself as someone very emotional or attached to other living beings.

Still, it makes you wonder about *lives after death*, not the supernatural ones... but the lives of those who outlive you.

*How do they remember you?*

*Did your life mattered? Now that you disappeared what has changed?*

More than one year later, we still have our cat litter in the same place where Lady was using it: it's not that we're paying respect or anything, we're just quite sloppy and slow to get rid of useless things... still, it's something that prompts me to think again of what I'm missing.


Ok, so why didn't I write this before? What happened last June?

Well, I already mentioned that I had some problems with the hardware... just look at this picture:

{% img center /images/sheevaplug.jpg 300 "my sheevaplug crazy wiring" %}

Basically, the first pin, the one that actually powers the cable, was nonfunctional in the sheevaplug I got... just to get it to run an usb key, I had to make that weird loop through an usb hub.

Obviously, at the time, I tried to contact GlobalScale's tech support, but I was only able to make a brief call through skype... their only email address available was the sales one, and I got no response from that.

Lesson learned: if you buy consumer goods overseas, don't expect much support, if the good is worth more than they're willing to blindly reship at the first customer complaint (I bought some videogames that, after being lost mid-voyage, the seller had no problem to send them again -successfully, this time- ).

On top of that, the sheevaplug has an armv5 cpu, and it shipped with a beta version of Ubuntu 9.04... though luck: Ubuntu supported only armv6 or greater with Ubuntu 9.10, and with 10.04 it moved the goalpost even further to armv7.

So, to hope to get any security updates for any meaningful length of time, without going to an even less familiar land, the best choice was Debian 6, Squeeze... obviously, to install an alternate os I had to [update the builtin bootloader: U-boot](https://www.google.com/search?hl=en&q=sheevaplug+uboot).

To further complicate my life, at the time I tasted the almost-effortless (but not perfect) experience of Time Machine backups on MacOSX, and so I wanted to replicate the same thing, both for my Linux system and for my MacOSX partition (which I got rid of since then, due to having space as a premium on an SSD). To do it I had to use [Netatalk](http://netatalk.sourceforge.net/) but at the time there were no precompiled ARM debian packages for it... I tried both to crosscompile it and to compile it directly on the sheevaplug, long story short: it was too much of an hassle.

The plan was to use it as network share (also for backups), local apt mirror, Squid proxy, web server, ssh tunnel, etc... Except for the Netatalk sharing (for linux I was satisfied with NFS) and the Squid proxy, I got all of this working; but the script I used to sync the apt mirror was failing often and I wasn't using that many machines contemporarily to warrant its use. Moreover the lan backup was weirdly taking too much time (the software I was using probably assumed RTT of a local disk, not a network one).

At the end, due to these shortcomings, I never really made use of the 3 different lvm logical volumes I had on the sheevaplug, now I hear you saying:

*What? LVM on a sheevaplug?*

Well, yes... it was also a learning exercise after all, and it seemed nice to use volumes like these instead of some other quota mechanism... then again, the only physical volume resided on an external usb hard drive, so don't think too much about it. :P

So, the technical problem I got was that the lvm volumes weren't being mounted anymore, and I had to manually remount them each time (possibly by resetting the device, since I kept lots of system files, like the ones in /usr/share, on the usb drive)... The drive and the lvm volumes alone seemed to be ok, since I could keep them mounted undefinitely on my desktop machine: A posteriori, this might have made me doubt about some other problems with the arm box (memory? usb?).

So, I finally managed to get my still-unstyled blog application online with the Cherokee web server and the Postgresql db. I planned to use sqlite at the beginning, due to the very small audience, but I couldn't run wsgi with a single worker, and multiple workers were incompatible with sqlite. But then I had to move all the data from the usb drive to a smallish (2GB) usb flash memory.

This was almost all fine and dandy, except that I never managed to get Cherokee and Postgres (upon which the workers depended) to automatically start at boot time, and weirdly there was absolutely no error on the logs... But given that I wasn't rebooting it that much often, I just ignored it, until...

The problem I mentioned at the beginning happened: now I couldn't start Postgresql anymore... this meant I couldn't even use pg_dump for the backup.

I resorted to manually backup the cluster files, and then I tried to purge and reinstall the package, but apt-get kept failing... I don't know if it was again a systemic problem or if instead it was a [known problem with the debian postgresql packaging](https://bugs.launchpad.net/ubuntu/+source/postgresql-8.3/+bug/235379/comments/15), but I was wary of running the fix of "find and delete everything postgre-related" since I wasn't able to recover my data just yet...

To make things worse: when I finally managed to get a postgresql install loaded with the cluster from the sheevaplug on my desktop machine, I sadly realized that it wasn't compatible: it was compiled with different float optimization, and basically an x86 db, x64 or arm db are all mutually incompatible with each other due to that. Now that I've done a bit more coding with binary files, I'm a little bit skeptical about the technical reasons underlying that incompatibility: Just look at Python's [struct module](http://docs.python.org/3/library/struct.html), the only way you can get something that is platform dependent, is if you ignore all the flags that let you specify the endiannes of the data, and if instead you roll with the native choice... It seems to me almost like that somewhere in the Postgresql code, there's a struct that's mapped directly to disk; but obviously it won't be this easy, I expect that code to have several layers of abstraction.


So, I got stuck with a non-working postgresql install, a possibly flaky hardware, a backup I couldn't access and a half-finished blogging software... no wonder that I wasn't feeling motivated to get back to blogging.

At the end, I realized that the very blog software I started writing, while a learning experience, was also not really what I wanted to do: what I wanted to do was to make something that other people could appreciate and use, and without any other people to motivate me to finish it, it just languished.

In fact, I failed to keep plain-text backups of my blog content, I added code to handle diffs between one post and another, I added OpenID login for the admin interface... I was basically rebuilding something that I could've got for free by using a VCS and ssh access, just like what's being used by the several static site generators available on the net.

And then there're the obvious benefits of static sites: negligible server load, and a lot less moving parts that can possibly go awry... more frugal and ecologic even :) I already planned to avoid handling comments on my own with my blog software, so relying on Disqus or similar services wasn't even a problem.

With that said: yesterday I whipped up Qemu, created an ARM debian box, sweared a bit at some other unintuitive postgresql errors, and finally got my data back... and Now I finally started blogging again, this time as an [Octopress](http://octopress.org/) blog running on github pages.

It isn't perfect, and I already witnessed a small problem that seems undocumented: markdown files with spaces in the name break the hyperlinking. But overall I'm satisfied with moving to a simpler, more popular, and -ultimately- saner solution :)

So, where does this leave me? In this last year a lot of small things happened, but I'm still not in the place that I want to be... but limiting myself with the bigger topic of this post, among the consequences of such an ordeal, I now grok better this [nugget of wisdom](https://news.ycombinator.com/item?id=5691216).

In fact, I think that I should refrain from pursuing projects, if those aren't truly needed by other people, and if those people aren't around to motivate me to work on them. I should focus more, and to do that, one obvious approach is to start learning less of the random things that I stumble upon almost daily.

Another thing, is a small and healthy distaste for arm machines. I mean: I have an arm smartphone, they can indeed be more power efficient, and they apparently got most of the mobile market... but if you're thinking of buying one for your personal use, you should ask yourself: *Do I really need it?*

If you're building a company on top of that hardware, by all means: go for it. But otherwise, I can't avoid but think that I could've spared myself many hassles by developing my small home server on a simple x86 box, maybe a small netbook (it isn't easy to get a cheap and small enclosure + microitx box from what I've seen)... that could've been much more reliable than my sheevaplug, while slightly more power consuming (and it would've even been easier to use locally, since I could've avoided the flaky usb debug port to be used through screen).

This very thing also applies for the recent craze about RaspberryPis; I have seen a friend using one as media center, but still he was complaining that not always the frame-rate was perfectly smooth (just to point out that's not perfect for every purpose). You should ask yourself: *Am I already doing something that could benefit from the smaller physical size and power footprint of such a machine?* If you aren't I think it's better to start with an existing prototype of such a software/system, and move to ARM only when you really need it.

Now [Linus Torvalds' opinion on *odd-ball machines*](http://torvalds-family.blogspot.it/2009/01/odd-hardware.html) strongly resonates in my brain :)
