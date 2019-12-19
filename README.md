# SlimeWhitelist
Whitelist plugin with autokick after max offline time.

This plugin is automaticly system, what can purge your server's whitelist with specified period.
Plugin stores latest players quit dates in sqlite or mysql (configurable) database.

How purge task work:
- Plugin send sql query to database;
- SQL remove entries where offline time > limit offline time from plugin config;
- Information about player removing will be show in console;
- If purge was called by '/swl purge' command, command sender will receive message about count of removed entries;

You can edit all messages in message.yml and configure plugin in config.yml, generated after first plugin launch.

Download my plugin in 'releases' tab or compile jar from sources (with javac for example) and enjoy!
