---
layout: post
title: "Tabs vs Spaces"
date: 2014-06-07 21:52
comments: true
categories: [programming]
---

Historically I've been one of the few people who consistently preferred to use Tabs over Space (I'm obviously talking about the characters: 0x9 against 0x20).

People usually contest that "any decent editor" makes using spaces just as comfortable as using tabs. I beg to differ: while editors don't have any problem in adding `n` spaces when pressing the tab key, and configuring to delete 4 indentation spaces when at the beginning of the line with a single press of del/backspace, you still have to press the arrow keys `n` times when navigating the code. I use emacs and sometimes I use vim, but I never really got the bindings for jumping around the text truly ingrained in my muscle memory.

Since this is an issue mostly with the whitespace sensitive languages, like Python and Haskell, you might argue that usually you don't need to navigate the code like that... a small example:

```python
class A:
    def f(self):
        pass

```

Now let's assume that I want to go up to the `d` of `def`, open a newline and insert a new decorato and that my cursor is at the end of the `pass` token. You can move to the previous line and then go to the beginning, or do it the other way around: go to the width corresponding to the beginning of the previous line and then move to it. Since I prefer to avoid seeing the cursor jump left-right (I don't like to have "phantom whitespace" after the end of the line), usually I do the latter. By pressing the home key I might get at the beginning of the line *after* the indentation (at the `p`) or *before*, but this means that I still have to navigate one indentation level. With tabs this is just a single keypress in either direction, but with spaces, I have to press it multiple times. Worse: since it isn't a border of a token, there're no obvious keybindings to jump right to it.

Obviously it's possible to write some emacs lisp or vimscript to do it, but I never found that itch worth scratching enough. Also: if I was really accustomed to vim I could just move around the code in a different way, but alas, that's not the case, and often I might want to edit some small snippet in a webbrowser or other platform different than vim. On the other hand, all the whitespace sensitive languages have good enough support in these editors that you can do simpler tasks (like indeting/dedenting a block of code) with a single keybinding, regardless of tabs/spaces.

One complaint about tabs is that spaces are needed for alignment, and thus this could lead to mix tabs and spaces, especially in Haskell: developers are used to align the code to the previous lines (otherwise it won't even compile, unlike with Python), but the truth is that if you put a newline just after the `=` signs, you can just use tabs to increase the indentation, without ever worrying about the alignment.

People usually are also opposed to tabs since it makes the code display differently on different machines: this is misguided, since **as long as you're using only one between spaces/tabs** all the indentations will always be aligned, regardless of the whitespace char you chose from. But using tabs will give you the advantage of choosing how much space you'll spend by indenting, without imposing your choice on the other developers. Obviously, the feature of changing the display size of tabs depends on the editor: in some it's easier than in others, and in other cases (like web browsers) you can't even do it, afaik... and you'll be stuck with 8 spaces for a tab, or something similar.

Emacs at times really strained my patience: a fellow programmer recently told me: *"it's supposed to be extensible, but it's not written anywhere that it should be coherent in its behaviour"* when I showed him how the official Forth mode used tabs even when `indent-tabs-mode` was set to `nil`. Other problems plagued the different python-mode availables: pressing tab to indent a new block, or using the indent/dedent commands at times used different whitespace characters (probably because it was hardcoded to use spaces in certain cases).

So, the crux of the issue is not using different whitespace chars, and it's interesting to think about what use the whitespace fulfill, and what choice do we have available: In a sense, as long as we store information as text, we'll use a bidimensional layout. this requires something to imprint a vertical direction, and an horizontal direction. The vertical direction is obviously supplied by the newlines: each time we want to push down vertically, it's a newline; but this leaves us with the horizontal separator, and if you think about it you don't really **need** more than one of this kind.

Yes, space have a fixed width, and tabs are useful for alignin tables on terminal outputs or something like that, but they won't work if your lines are skewed by more than 8 (or the number of spaces equivalent of a tab in your terminal) characters; unless you use multiple tabs, but if you know in advance how many tab characters you need, you should be able to do the same with spaces (like with the `.rjust()` and `.ljust()` methods for python strings).

So, we could just write code and separate each and every token with `\t`, and get rids of spaces altogether! (since I think that tabs are the superior alternative) But nobody's going to do that.

So, I said we only need 2 whitespace characters, and yet we have spaces, tabs, vertical tabs, newlines... ASCII is a fact of life, and we won't get rid of baggage that we don't use soon (just like the the Bell character), so we might just be better off by standardizing on some, and avoid hassles.

And that's why I'm writing this post: even if historically I always pushed for my preference, and some of my friends are reminded of me also for my "foolish" preference, I always cared about a common standard and the path of least resistance. My goto language for a lot of time has been Python, and its [PEP8](http://legacy.python.org/dev/peps/pep-0008/) unfortunately just acknowledged that spaces were used more often, and in no case the PEP8 alone was an excuse to change pre-existing code. So the few times when people complained that my code was not following the PEP8, and that I shouldn't bother trying to properly configure Emacs to work with tabs (and just convert back the file to tabs, if needed), I felt obliged to point them that there was no clear-cut standard, but only a vague consensus.

But last year, the [PEP8 was updated](http://hg.python.org/peps/rev/fb24c80e9afb), and luckily it now explicitly states that spaces are to be preferred. So, even if my pet peeve was in the other direction, I'm heavily relieved that now *There should be one-- and preferably only one --obvious way to do it.* also for this issue :) and in fact some months ago at last [I updated my Emacs' init.el](http://bazaar.launchpad.net/~berdario/+junk/dotfiles/revision/63).
