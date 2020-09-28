# OpenWeatherApp

This app was developed using TDD. There should be a fair amount of code coverage, though nearer the end I started to rush and may have started to miss some opportunities for unit tests.
I have used the MVP pattern as well as making use of a service provider pattern to abstract away the need to tightly couple to a location (Android GPS) or network (Retrofit) service.

You will notice that in the `WeatherDataProvider` I have made use of a `BackEndTask`. This is a class I have created and something I am experimenting with at the moment as a potential solution to [callback hell](http://callbackhell.com/). The reasoning for such a class is that it can be returned through multiple classes without having to instead daisy-chain callbacks.

You may ask why I haven't used something like RxJava to achieve a similar result. Although I very much appreciate the complexities that RxJava solves, I generally like to enforce a rule upon myself to write code that is readable enough for a computer science student to interpret and at least grasp an understanding of the business logic. I feel that RxJava can significantly obfuscate this (plus, I'm also not a massive fan of how pervasive `Observables` can be throughout a cosdebase if not properly managed).

I have made an assumption in the design of the app that it would be ncie to continue seeing the current and cached weather data while new data is being fetched. As such, while new data is being fetched, info text keeps you aware of the current running processes.

With more time I would like to:
* Clean up the glide image call downloading the weather icon
* Improve upon the way that location calls are being handled
* Move the api key to a remote config so as to not be hosted on the app (or visible at all on GitHub for that matter)
* Use Dagger/Hilt for dependency inkection in `MainActivity`
