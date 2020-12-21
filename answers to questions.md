# Questions
----------------------------------------

**NOTE** - I actually used my github account for source tracking - https://github.com/lukeboy1111/api-example-ratpack (I had to push my windows machine into service, and it was better to use github, I'll grant access to it). 

###1. How long did you spend on the coding test? What would you add to your solution if you spent more time on it? If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.

A lot longer that I'd planned. Although ratpack is good as a framework, and I've only really scratched the surface and had to learn much through the way, I found that the documentation is massively thin and is also massively skewed towards Groovy development. By that time I was too far down the Java road to refactor everything.

I also wasted about 3 days trying to get redis to work using the framework itself. You'll see that I figured out the configuration side of it, but ended up with hardwiring a Jedis implementation in place to make it work - in time, I'd engineer this out so that it was much cleaner.

Once that happened, I also wasted masses of time trying in vain to fully implement [RatpackPac4j] (https://ratpack.io/manual/current/api/ratpack/pac4j/RatpackPac4j.html) - what an absolute trial that was!

Some of this was down to my work environment. My Mac Book that I normally use has died on me, bad timing, so I hurriedly had to reconfigure my windows laptop and get it all working. It's not been the best week!

Lots of things I ran out of time for that I'd add:

i. **Unit Tests** - because I wasted lots of time figuring stuff out, I've just simply run out of time to do full unit test coverage. Apologies, but it's something I always do, without fail. I've even got my own copy of "Clean Code" by Uncle Bob btw.
ii. **Refactoring** - I probably would refactor some of the objects to be slightly better laid out and I'd remove some of the hacks that I ended up leaving in place (I couldn't quite figure out from the ratpack documentation to access the token itself rather than the Authorisation header, most likely via JWT is the answer) - and of course, make redis completely configurable and stop using Jedis.
iii. **Gson** - I've left Gson in on purpose, I have actually found it better than Jackson as my personal preference - originally I'd planned to rip that out to be consistent over the project.
iv. **JWT** - It's actually not "True" JWT token checking, as this also doesn't seem to work with Pac4j and the documentation on this again was as scant as a caveman's wardrobe.
v. **Docker / Kubernetes** - I've created an embryonic build for Docker process, and I'd likely use https://github.com/qaware/gradle-cloud-deployer to deploy to Kubernetes. I may actually complete this shortly, as I'd like to have done all of this for you too - but, because of the time above, I've totally timed out on it - although I know that both wil definitely work! :)
vi. **Functionality** - I'd have created a sequence to map to tokens, saving us having horrendous key sizes and I'd actually move to using MongoDB to store transactions with Redis working as a forward cache.

In normal circumstances, I wouldn't allow myself to get sidelined for so long. I did moan at Jaco and he mentioned that he too couldn't get redis working and mentioned Jedis, so that made me just use Jedis also, but he didn't provide any other help!

In general, though, I'm very tigerish about issues, I like to understand the cause and effects of issues - and so I'll generally work through and solve them. But that said - I'll ask for help or simply be collaborative if that's what's needed.

### 2. What was the most useful feature that was added to Java 8? Please include a snippet of code that shows how you've used it.

I love the streams API where you can chain things together to map stuff from type to another. Actually, since this task was relatively simple, I didn't really use anything like this here in it's more advanced format, but the ratpack API uses this for example like this:

```service.getTransactions(token).map(Jackson::json).then(ctx::render);``

There are more concrete examples of this in the documentation, but there's no doubt that it's kinda cool.

### 3. What is your favourite framework / library / package that you love but couldn't use in the task? What do you like about it so much?

For me, it's SpringBoot. I used to work with EJBs and the like, and SpringBoot just takes this to the next level. Decorating the code with annotations is nice, and means that you can have self documenting code (e.g. Swagger APIs).

### 4. What great new thing you learnt about in the past year and what are you looking forward to learn more about over the next year?

I'm not sure it's a great thing, but I learned about GoLang and gRPC. Both are interesting in their own right, and both have both upsides (in the case of both, performance) and significant downsides (GoLang forces you to to much of the manual set up that we take for granted these days). 

gRPC is also interesting, but for me it encourages tight coupling and I'm not sure that this is actually a good thing moving forward - it still feels a lot like a Friday afternoon project for me.


### 5. How would you track down a performance issue in production? Have you ever had to do this? Can you add anything to your implementation to help with this?

We've used various methods. The use of Prometheus and inserting measuring points are a good way of instrumenting apps and finding potential points of failure. We used Grafana to measure other aspects of our systems such as database and redis clusters again for the same measurements and detections of potential issues.

At Adobe, we used elastic search and splunk to help us diagnose issues that came up, you could track these down and use splunk to find the specifics of errors / performance issues, that's also useful, but potentially expensive.

A good example of how you can do instrumentation is [here](https://github.com/prometheus/client_java#counter)

### 6. How would you improve the APIs that you just used?

I'd spend more time producing some documentation and fine tuning the responses to allow for 401 errors to be returned. I'd take the time to fully understand RatPackPac4j also.

### 7. Please describe yourself in JSON format.

I created a call also for this: https://localhost:5050/luke

```
{
    "firstName": "Luke",
    "lastName": "Campbell",
    "age": {
        "age": 49,
        "actsAge": 12
    },
    "town": {
        "name": "Ramsey",
        "country": "Isle of Man"
    },
    "interests": [
        {
            "name": "Football",
            "geekFactor": 1,
            "description": "Scottish and English Football"
        },
        {
            "name": "Podcasts",
            "geekFactor": 5,
            "description": "The Hairdryer Treatment"
        },
        {
            "name": "NFL",
            "geekFactor": 5,
            "description": "The Chicago Bears"
        },
        {
            "name": "Fantasy Sports",
            "geekFactor": 10,
            "description": "I play in 10 leagues, winning two of them last year"
        }
    ],
    "spokenLanguages": [
        {
            "name": "English",
            "nativelySpoken": true,
            "basicKnowledge": false,
            "written": true,
            "spoken": true
        },
        {
            "name": "Scottish",
            "nativelySpoken": true,
            "basicKnowledge": false,
            "written": true,
            "spoken": true
        },
        {
            "name": "Italian",
            "nativelySpoken": false,
            "basicKnowledge": true,
            "written": true,
            "spoken": false
        },
        {
            "name": "Spanish",
            "nativelySpoken": false,
            "basicKnowledge": true,
            "written": true,
            "spoken": false
        },
        {
            "name": "Mandarin",
            "nativelySpoken": false,
            "basicKnowledge": true,
            "written": false,
            "spoken": true
        }
    ],
    "programmingLanguages": [
        {
            "name": "Java",
            "personalEnjoyment": 10,
            "variant": "",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "Perl",
            "personalEnjoyment": 10,
            "variant": "",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "Perl",
            "personalEnjoyment": 10,
            "variant": "mod_perl",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "PHP",
            "personalEnjoyment": 7,
            "variant": "",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "GoLang",
            "personalEnjoyment": 3,
            "variant": "",
            "coreLanguage": false,
            "basicKnowledge": true
        },
        {
            "name": "Python",
            "personalEnjoyment": 1,
            "variant": "",
            "coreLanguage": false,
            "basicKnowledge": true
        },
        {
            "name": "HTML",
            "personalEnjoyment": 8,
            "variant": "",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "CSS",
            "personalEnjoyment": 8,
            "variant": "",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "JavaScript",
            "personalEnjoyment": 10,
            "variant": "",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "JavaScript",
            "personalEnjoyment": 9,
            "variant": "TypeScript",
            "coreLanguage": true,
            "basicKnowledge": false
        },
        {
            "name": "JavaScript",
            "personalEnjoyment": 10,
            "variant": "ECMA Script 6 / 7",
            "coreLanguage": true,
            "basicKnowledge": false
        }
    ],
    "frameworksAndStuff": [
        {
            "name": "FreeBSD",
            "personalEnjoyment": 10,
            "isCurrent": null
        },
        {
            "name": "CentOS",
            "personalEnjoyment": 10,
            "isCurrent": null
        },
        {
            "name": "Windows 10",
            "personalEnjoyment": 8,
            "isCurrent": null
        },
        {
            "name": "OS X",
            "personalEnjoyment": 10,
            "isCurrent": null
        },
        {
            "name": "Debian",
            "personalEnjoyment": 9,
            "isCurrent": null
        },
        {
            "name": "Ubuntu",
            "personalEnjoyment": 8,
            "isCurrent": null
        },
        {
            "name": "Bootstrap",
            "personalEnjoyment": 9,
            "isCurrent": null
        },
        {
            "name": "Angular",
            "personalEnjoyment": 10,
            "isCurrent": null
        },
        {
            "name": "React / Redux",
            "personalEnjoyment": 10,
            "isCurrent": null
        },
        {
            "name": "SpringBoot",
            "personalEnjoyment": 10,
            "isCurrent": null
        },
        {
            "name": "Ratpack",
            "personalEnjoyment": 8,
            "isCurrent": null
        }
    ]
}
```


### 8. What is the meaning of life?

If you read Douglas Adams, it's 42 (including the universe and everything). I'm not exactly sure what the answer to life is except that you should try to be kind, and the best possible version of you that you can be. It's not much of an answer, but it's a work in progress....
