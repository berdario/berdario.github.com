---
layout: post
title: "Let down by Google's Android"
date: 2013-12-24 23:56
comments: true
categories: open source,android,cyanogenmod,security
---

Originally I wanted to write another post, akin to "Cyanogenmod is a ghetto". In fact I wasn't really fond of that project and, until now, with my android phone (a Nexus S) I chose to stick to the stock android version (I experienced cyanogenmod and others aftermarket mods with my previous phone: an HTC Dream, the very first android device).

I never really liked the xda-developers forums either: a lot of OSS projects have their own forum, but for development a Mailing List (or similar) is preferred. The advantages are obvious: you get to reuse your identity (email), you don't have to deal with wonky cookie-based session authentication, you don't have to deal with bulky signatures/animated gifs/etc. (as long as you don't render html and/or enforce the netiquette) and everyone keeps a copy of the old discussions (thus, if the server and its backup disks get corrupted you won't completely lose your project history).

In fact, at the beginning I remember developers complaining about the "cooking" mindset: building your homebrew android versions wasn't supposed to be like taking binaries and stuff them together without really knowing how the whole system worked (this was probably more common during the Windows Mobile 6 roms era). Most people on xda in fact were quite new to linux: I remember asking there (or in other places, like some irc channels) why the preferred filesystem for app2sd was ext2 instead of ext3/4... but the answer I got was just that journaling "wasn't needed" for a mobile device (nevermind the fact that even mobile devices can get turned off abruptly, or just think about the long check process that android does when it starts up to detect corruption on the sd/external memory). Needless to say: the conventional wisdom changed.

Maybe for the same reason, when I asked about a [vanilla](https://en.wikipedia.org/wiki/Vanilla_software) Android rom I was told that there was no such thing as vanilla. At the time I thought that maybe it was just because there was indeed no such build available (but still, it seemed strange that no-one would take the AOSP sources and made it available as an image ready-to-install), but after I realized that it might have been quite likely that the very idea of a "vanilla" version, might have been foreign to them... unbeknownst of the dangers of balkanization for the several android roms that sprouted out.

Also, due to the fact that end-users (who never flashed a firmware before in their life) and developers hanged around in the same forums, I remember that people could get frustrated and hostile to the users at times. Unfortunately, digging up posts from the xda-dev and cyanogenmod forums before summer 2009 seems quite hard.

Another problem, was the [handling of Google Apps](http://android-developers.blogspot.it/2009/09/note-on-google-apps-for-android.html): imho it was obvious from the start that if someone is willingly licensing something to be used freely, the software that they haven't licensed so is probably important for them. But early modders probably didn't care, due to being accustomed of redistributing binaries in the old Windows Phone 6 days...

I kept using my HTC Dream until early 2012, and while it was painful to use due to the small amount of memory, it was still usable with Cyanogenmod5 (running Android 2.1 aka Eclair). With Cyanogenmod6 (Android 2.2) instead, even if it was [still supported by Cyanogen himself](https://github.com/CyanogenMod/android_vendor_cyanogen/blob/froyo/CHANGELOG.mkdn#maintainers), there were some changes among the system apps, like the new Gallery that (due to its additional animations and increased memory requirements) was totally unusable on the Dream. Other than this, there were also some nasty sudden reboots when using the camera (but it was probably to be expected, since the camera firmware has almost never been open). This left me with a bitter taste, and I was not so sure anymore that the Cyanogenmod they shipped was actually used and tested day-to-day by the developers (not for the older models, at least).

Between an up-to-date device that's so slow as to be unusable, and a device that's some versions behind, I chose to just use the latter. Up until then, I coveted the latest Android version, but let's look at the facts: There were some truly important and interesting improvements with the earlier versions:

- 1.5 3rd party keyboards, widgets, better bluetooth, copypaste in the browser, animations, autorotation, etc.
- 1.6 Search (also with voice) through contacts and more, TTS, camera improvements...
- 2.0 Multiple accounts sync, browser improvements, live wallpapers, etc.
- 2.2 JIT, USB & Wi-fi tethering, Market batch updates, native app2sd, etc.

But with the latest versions, I don't feel such a need for the new features. I'm not saying that changes in the later Android version aren't interesting, for example I appreciated the multiple user accounts support in 4.2 and 4.3. But if you weren't an Android user before 2.2, just try to imagine how it would've been not to be able to update all the dozen (or more!) of applications that requested an update... Or to resort to rooting just to get tethering or app2sd working.

In the meanwhile, though I didn't "need" Cyanogenmod anymore, it has improved: the controversial idea of rooting your device [has been reevaluated](http://www.cyanogenmod.org/blog/security-and-you) (is subverting the android security model a good idea? ["How does the SU app really work?"](https://github.com/ChainsDD/Superuser/) I find it slightly worrying that there's no wiki on the official project and aside from stackoverflow.com it's difficult to find information on it)

I've also heard that they're planning to fix the [building keys detail](https://android-review.googlesource.com/22694) and the Cyanogenmod Inc. project (aside from [some brouhaha](https://plus.google.com/+GuillaumeLesniak/posts/L8FJkrcahPs) ) seems like good news.

So, I'm not a fan of Cyanogenmod (though I'll go back to using it now, probably), but here I actually wanted to talk about what disappointed me with Google's Android management recently:

To better understand the issue, you might want to have a look at [this explanation of the Android security model](http://nelenkov.blogspot.de/2013/05/code-signing-in-androids-security-model.html). Briefly: Apk signatures are checked with a TOFU (Trust On First Use) model and there is no centralized CA model.

[13th of February 2013 a fix was pushed](https://www.codeaurora.org/cgit/quic/la/platform/libcore/commit/?h=jb_2.5.6&id=c5af994b579b404cb0e501e7e20b5427493d66f7) to an internal Google repository. [The issue it fixed was not publicly known until early July](http://bluebox.com/corporate-blog/bluebox-uncovers-android-master-key/), but as of 11th of July Google still didn't merge the patch into the AOSP.

You'll notice that the first link isn't from AOSP: I got the link by asking on the Cyanogenmod irc channel, and that is where Cyanogen got the patch from (it probably was just by luck that this commit was publicly available). That was subsequently [merged into Cyanogenmod 10.1](https://jira.cyanogenmod.org/browse/CYAN-1602).

This irks me already: Google receive news of a security vulnerability and has a solution for it, but decides to sit on it for 5 months? You'd think that the wait serves for 3rd parties to have time to integrate it into their Android builds, test it and ship it; but as far as we know, OEMs aren't backporting security fixes from Google's internal repository. In fact the fix has been integrated only in devices that run at least Android 4.3.

But the latest supported version for my Nexus S is Android 4.1, 32 months passed since the Nexus S launch, am I already at risk of widely known exploits for an unpatched bug? (Actually it's even worse: the latest update shipped October 2012, less than 2 years since the device launch)

Google would like for us to think otherwise [[1]](http://arstechnica.com/security/2013/07/google-patches-critical-android-threat-as-working-exploit-is-unleashed/) [[2]](http://arstechnica.com/security/2013/08/researchers-find-trojanized-banking-app-that-exploits-critical-android-bug/). Sure they can fix part of the issue server side on the Play Store, but what about Android at-large? What about apps installed outside the market? They're more common than you might think: just realize that some devices don't ship with the Google Apps and that several alternative stores exists (F-Droid, Amazon's, even the Humble Bundle App works as an app store even if you cannot buy directly from it). The cornerstone for bringing relief to otherwise unsupported devices seems to be the Google Verification Tool.

**Edit:** *I tried again, and now the Verification Tool is able to recognize this exploit. Needless to say, when I prepared my notes for this post some time ago it wasn't, and my point still stands.*

<strike>Unfortunately, it doesn't protect from this security hole at all. I have no clue why is that, but it's quite easy to reproduce: You just have to take an android package, decompile it and repackage it like [this post](http://blog.apkudo.com/2012/10/16/reverse-engineering-android-disassembling-hello-world/) instructs you to do (it even has a link to an already-built HelloWorld apk, so you don't even have to write Java code to test it) and instead of rebuilding the apk straight away, follow the instructions as laid out in this [POC shell script](https://gist.github.com/poliva/36b0795ab79ad6f14fd8) (or just execute it): </strike>

{% include_code android-apk-poc.sh %}

<strike>Then, make sure that the Google Verification Tool is enabled (before 4.2 you have to open a separate application called Google Settings, you'll find a checkbox under Verify Apps) and you should find that your hacked apk installs just fine. (Actually, I got GVT to error on me when trying to install legitimate packages from F-Droid, so I'm really skeptical about its real-world usefulness)

You should've already realized it by now, but it might be worth repeating: this would permit an attacker to silently reuse permission granted to a legitimate application, or steal data that was stored by it, without the user being the wiser.</strike>

This is the last straw that made me wary of Google management, but it's not the first time I've been dissatisfied by it: [this article](http://arstechnica.com/gadgets/2013/10/googles-iron-grip-on-android-controlling-open-source-by-any-means-necessary/) clearly exposes the problem:
Replacing open source applications with closed source ones. I felt it especially with the transition to Hangouts: it made it impossible to message your contacts on federated XMPP servers, it superseded GTalk which was mostly open source afaik and it broke Hangouts inside the G+ app if you tried to downgrade it to GTalk. Along with GTalk, the Search app, Music, Calendar, Keyboard, among others have been superseded by their closed-source equivalent.

Sure, the code of the old app is still available (it's what [ChatSecure](https://chatsecure.org/) has been built on if I'm not mistaken), but if nobody is using and developing the code it'll likely bitrot. Also it won't be featured anymore on the builds that have represented the true stock Android experience until now: the builds that were shipped on Nexus devices that is.

Slowly it's hijacking the perception of what actually Android is. A true vanilla AOSP build without Google apps might soon be unusable for someone trained on Google devices.

So, Android is Open Source, and I'm grateful for that... I've always thought that the critiques of the Google Code dump releases as not being truly-open were quite shallow, given that the competition was much worse (that is, not open source at all, no sideloading of apps, not Linux friendly, no freely available developer tools). I certainly wished for a more open engagement with the community, but it seemed good enough.

I don't know if the open source contenders (Ubuntu phone, Jolla Sailfish and FirefoxOS) will succeed, and I realize that the security updates mismanagement that I'm blaming Google for can actually have some partial explanations with the way that software deploy is done on such devices.

(I talked recently with developers for Ubuntu phone, and they told me that the idea of shipping a huge image that contains both feature updates and security updates, and doing it "late" is due to user experience and usability requirements: testing is paramount and users don't want to apply system updates too often... I'm not 100% persuaded, but surely it's a case for having an as-smallest-as-possible core system)

But if we actually have some freedom in this mobile platform, we have to exercise it. We have to test where the boundaries are and realize where they are. Has Google an unfair competitive advantage with the Google Apps licensing? Given the proprietary model, even if it has not, it's possible in the future for them to make it so. Do you even have [Freedom 0](https://en.wikipedia.org/wiki/The_Free_Software_Definition#The_definition) if Google doesn't allow the redistribution of the Google apps? (I'm using the FSF idea of freedom: I don't completely agree with Richard Stallman, but I find his definition of software freedom useful for bringing forth discussion)

Using Android without Google apps (thus without even the Play store or the APIs) would make it clear when to get your work done you have to go through proprietary software, and force you into a platform that you couldn't  build/recreate yourself. If only as an experiment, I think I'll try it soon, but it's obvious how avoiding the use of the market can be troublesome.

In fact, even some very OSS-friendly developers and applications [don't ship outside the Play store](https://github.com/WhisperSystems/TextSecure/issues/127) (but luckily [Cyanogenmod will bundle this particular application](http://www.cyanogenmod.org/blog/whisperpush-secure-messaging-integration)). The reasons are somewhat complex: Moxie objects to the key handling of the F-Droid project and the requirement for enabling 3rd party sources, but it seems that the main reason that is blocking that bug is guaranteeing updates and having crash reporting (which could be solved from inside TextSecure).

While on the front of removing the sideloading requirement, an [effort is currently underway](https://gitorious.org/f-droid/fdroidclient/merge_requests/37) to get F-Droid running as a system app (possible when rooting or bundling it into your image), thus hopefully the future for a truly open Android is not so bleak.

**Update (2014/07/05):** I lasted some months, but now I think I'll yield. A similar sentiment to my post has been echoed in widely-read posts like [this one](http://lwn.net/Articles/602521/) (I still have to try out Kolab, but it seems like a solid alternative and I'll probably use that in the near future), I've been able to do most of what I want to do with my device, but other things have been quite a pain. Case in point: walking directions; since OsmAnd has poor search functionality (I don't need fancy things like "looking for food" or "looking for a certain shop", I just need a simple address search for when the GPS isn't readily available, and you don't know exactly where are you directed to) I chose to rely on Waze for my navigation needs. Installing Waze was already a pain on its own, since old versions just crash, and the Amazon app store version is outdated. I asked support and they pointed me to other appstores, but from the quality and other details I suspect that these other stores are actually downloading the apks from Google Play on their own, to make them available again on their channel without any input from developers. Not a great alternative to the Google store, if that's their submission/QA process.

But obviously Waze is mostly thought for car navigation. Using it to walk around in one-way roads is definitely not what it was built to do, and it's mind numbing to endure.

Again: Some applications crash, all the websites who advertise a mobile app either link directly to the Google Play website (or send an intent which will be picked up by F-Droid thus giving back an empty results page), and there was the occasional meaningful app only available through the Play store. I expected that, and I'll try to nag fellow developers about it in the future, but living it is kinda depressing... it's a constant reminder that the battle is already lost.

What was probably the last straw was being in one of the cities served by all those alternative taxi service: Flywheel, Uber, Sidecar and Lyft. Needless to say: all of these require the Google Play services. Given that the only thing that they actually require is Google Maps (and which could be avoided by simply leveraging OSM) I think that these companies should definitely deserve a slap on their wrist for not even trying to make a freely available alternative: after all, geolocation javascript APIs have been available since 5 years, and so integrating a webview with Google Maps (if not a full and complete webapp) should definitely be feasible.

The sorry state of the alternatives app store make it so that essential requisited of the apps (like the presence of the Google Play services itself) isn't listed and made known, and thus you'll find out downloading apps that crash or fail to install without any useful error message.

I don't think of giving in completely to Google again. I just recognize that I feel it's more difficult to give up Google's Android than Microsoft's Windows. And just like it was for me and almost all the current Linux users, who started by dipping their toes in the Linux waters by dual-booting and only gradually letting their need for Windows wither, I think that the solution in this case would be to dual boot: Use a free mobile OS normally, and switch to the proprietary one only when you truly need it.

Due to the always-connected nature of our modern mobile phones and the lack of solid dual boot solutions, I'm afraid that the dual-boot will just take form as having 2 devices. At first I thought that having a separate tablet for games with the Google services might fill that role, but apps that require being on a mobile phone (like the alternative taxi-services and textsecure) make it a much less attractive proposition.
