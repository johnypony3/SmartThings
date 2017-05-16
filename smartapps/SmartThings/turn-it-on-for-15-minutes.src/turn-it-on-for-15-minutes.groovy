/**
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Turn It On For 5 Minutes
 *  Turn on a switch when a contact sensor opens and then turn it back off 5 minutes later.
 *
 *  Author: SmartThings
 */
definition(
    name: "Turn It On For 15 Minutes",
    namespace: "smartthings",
    author: "SmartThings",
    description: "When a switch is turned on, it is then turned off after 15 minutes.",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_contact-outlet.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_contact-outlet@2x.png"
)

preferences {
	section("Turn on a switch for 15 minutes..."){
		input "switch1", "capability.switch"
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
    subscribe(switch1, "switch.on", switchOnHandler)
}

def updated(settings) {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
    subscribe(switch1, "switch.on", switchOnHandler)
}

def switchOnHandler(evt) {
	switch1.on()
	def delay = 60 * 1
	runIn(delay, turnOffSwitch)
}

def turnOffSwitch() {
	switch1.off()
}