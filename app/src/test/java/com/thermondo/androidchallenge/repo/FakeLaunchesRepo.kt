package com.thermondo.androidchallenge.repo

import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.common.fromJson
import com.thermondo.androidchallenge.features.core.data.dto.LaunchResponse
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.features.home.domain.repo.LaunchesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLaunchesRepo : LaunchesRepo {

    val bookmarkedLaunches = mutableListOf<Launch>()

    var shouldReturnErrorResponse = false

    override suspend fun getAllLaunches(): Response<List<Launch>> {
        val launches = fromJson<List<LaunchResponse>>(testResponse)
        return if (shouldReturnErrorResponse) {
            Response.Error("Error message here")
        } else {
            Response.Success(launches!!.map { it.toLaunch() })
        }
    }

    override suspend fun addToBookmark(launch: Launch) {
        bookmarkedLaunches.add(launch)
    }

    override suspend fun removeFromBookmark(launch: Launch) {
        bookmarkedLaunches.remove(launch)
    }

    override fun getAllBookmarkedLaunches(): Flow<List<Launch>> {
        return flow { emit(bookmarkedLaunches) }
    }

    private val testResponse = "[\n" +
            "    {\n" +
            "        \"fairings\": {\n" +
            "            \"reused\": false,\n" +
            "            \"recovery_attempt\": false,\n" +
            "            \"recovered\": false,\n" +
            "            \"ships\": []\n" +
            "        },\n" +
            "        \"links\": {\n" +
            "            \"patch\": {\n" +
            "                \"small\": \"https://images2.imgbox.com/94/f2/NN6Ph45r_o.png\",\n" +
            "                \"large\": \"https://images2.imgbox.com/5b/02/QcxHUb5V_o.png\"\n" +
            "            },\n" +
            "            \"reddit\": {\n" +
            "                \"campaign\": null,\n" +
            "                \"launch\": null,\n" +
            "                \"media\": null,\n" +
            "                \"recovery\": null\n" +
            "            },\n" +
            "            \"flickr\": {\n" +
            "                \"small\": [],\n" +
            "                \"original\": []\n" +
            "            },\n" +
            "            \"presskit\": null,\n" +
            "            \"webcast\": \"https://www.youtube.com/watch?v=0a_00nJ_Y88\",\n" +
            "            \"youtube_id\": \"0a_00nJ_Y88\",\n" +
            "            \"article\": \"https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html\",\n" +
            "            \"wikipedia\": \"https://en.wikipedia.org/wiki/DemoSat\"\n" +
            "        },\n" +
            "        \"static_fire_date_utc\": \"2006-03-17T00:00:00.000Z\",\n" +
            "        \"static_fire_date_unix\": 1142553600,\n" +
            "        \"net\": false,\n" +
            "        \"window\": 0,\n" +
            "        \"rocket\": \"5e9d0d95eda69955f709d1eb\",\n" +
            "        \"success\": false,\n" +
            "        \"failures\": [\n" +
            "            {\n" +
            "                \"time\": 33,\n" +
            "                \"altitude\": null,\n" +
            "                \"reason\": \"merlin engine failure\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"details\": \"Engine failure at 33 seconds and loss of vehicle\",\n" +
            "        \"crew\": [],\n" +
            "        \"ships\": [],\n" +
            "        \"capsules\": [],\n" +
            "        \"payloads\": [\n" +
            "            \"5eb0e4b5b6c3bb0006eeb1e1\"\n" +
            "        ],\n" +
            "        \"launchpad\": \"5e9e4502f5090995de566f86\",\n" +
            "        \"flight_number\": 1,\n" +
            "        \"name\": \"FalconSat\",\n" +
            "        \"date_utc\": \"2006-03-24T22:30:00.000Z\",\n" +
            "        \"date_unix\": 1143239400,\n" +
            "        \"date_local\": \"2006-03-25T10:30:00+12:00\",\n" +
            "        \"date_precision\": \"hour\",\n" +
            "        \"upcoming\": false,\n" +
            "        \"cores\": [\n" +
            "            {\n" +
            "                \"core\": \"5e9e289df35918033d3b2623\",\n" +
            "                \"flight\": 1,\n" +
            "                \"gridfins\": false,\n" +
            "                \"legs\": false,\n" +
            "                \"reused\": false,\n" +
            "                \"landing_attempt\": false,\n" +
            "                \"landing_success\": null,\n" +
            "                \"landing_type\": null,\n" +
            "                \"landpad\": null\n" +
            "            }\n" +
            "        ],\n" +
            "        \"auto_update\": true,\n" +
            "        \"tbd\": false,\n" +
            "        \"launch_library_id\": null,\n" +
            "        \"id\": \"5eb87cd9ffd86e000604b32a\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fairings\": {\n" +
            "            \"reused\": false,\n" +
            "            \"recovery_attempt\": false,\n" +
            "            \"recovered\": false,\n" +
            "            \"ships\": []\n" +
            "        },\n" +
            "        \"links\": {\n" +
            "            \"patch\": {\n" +
            "                \"small\": \"https://images2.imgbox.com/f9/4a/ZboXReNb_o.png\",\n" +
            "                \"large\": \"https://images2.imgbox.com/80/a2/bkWotCIS_o.png\"\n" +
            "            },\n" +
            "            \"reddit\": {\n" +
            "                \"campaign\": null,\n" +
            "                \"launch\": null,\n" +
            "                \"media\": null,\n" +
            "                \"recovery\": null\n" +
            "            },\n" +
            "            \"flickr\": {\n" +
            "                \"small\": [],\n" +
            "                \"original\": []\n" +
            "            },\n" +
            "            \"presskit\": null,\n" +
            "            \"webcast\": \"https://www.youtube.com/watch?v=Lk4zQ2wP-Nc\",\n" +
            "            \"youtube_id\": \"Lk4zQ2wP-Nc\",\n" +
            "            \"article\": \"https://www.space.com/3590-spacex-falcon-1-rocket-fails-reach-orbit.html\",\n" +
            "            \"wikipedia\": \"https://en.wikipedia.org/wiki/DemoSat\"\n" +
            "        },\n" +
            "        \"static_fire_date_utc\": null,\n" +
            "        \"static_fire_date_unix\": null,\n" +
            "        \"net\": false,\n" +
            "        \"window\": 0,\n" +
            "        \"rocket\": \"5e9d0d95eda69955f709d1eb\",\n" +
            "        \"success\": false,\n" +
            "        \"failures\": [\n" +
            "            {\n" +
            "                \"time\": 301,\n" +
            "                \"altitude\": 289,\n" +
            "                \"reason\": \"harmonic oscillation leading to premature engine shutdown\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"details\": \"Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage\",\n" +
            "        \"crew\": [],\n" +
            "        \"ships\": [],\n" +
            "        \"capsules\": [],\n" +
            "        \"payloads\": [\n" +
            "            \"5eb0e4b6b6c3bb0006eeb1e2\"\n" +
            "        ],\n" +
            "        \"launchpad\": \"5e9e4502f5090995de566f86\",\n" +
            "        \"flight_number\": 2,\n" +
            "        \"name\": \"DemoSat\",\n" +
            "        \"date_utc\": \"2007-03-21T01:10:00.000Z\",\n" +
            "        \"date_unix\": 1174439400,\n" +
            "        \"date_local\": \"2007-03-21T13:10:00+12:00\",\n" +
            "        \"date_precision\": \"hour\",\n" +
            "        \"upcoming\": false,\n" +
            "        \"cores\": [\n" +
            "            {\n" +
            "                \"core\": \"5e9e289ef35918416a3b2624\",\n" +
            "                \"flight\": 1,\n" +
            "                \"gridfins\": false,\n" +
            "                \"legs\": false,\n" +
            "                \"reused\": false,\n" +
            "                \"landing_attempt\": false,\n" +
            "                \"landing_success\": null,\n" +
            "                \"landing_type\": null,\n" +
            "                \"landpad\": null\n" +
            "            }\n" +
            "        ],\n" +
            "        \"auto_update\": true,\n" +
            "        \"tbd\": false,\n" +
            "        \"launch_library_id\": null,\n" +
            "        \"id\": \"5eb87cdaffd86e000604b32b\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fairings\": {\n" +
            "            \"reused\": false,\n" +
            "            \"recovery_attempt\": false,\n" +
            "            \"recovered\": false,\n" +
            "            \"ships\": []\n" +
            "        },\n" +
            "        \"links\": {\n" +
            "            \"patch\": {\n" +
            "                \"small\": \"https://images2.imgbox.com/6c/cb/na1tzhHs_o.png\",\n" +
            "                \"large\": \"https://images2.imgbox.com/4a/80/k1oAkY0k_o.png\"\n" +
            "            },\n" +
            "            \"reddit\": {\n" +
            "                \"campaign\": null,\n" +
            "                \"launch\": null,\n" +
            "                \"media\": null,\n" +
            "                \"recovery\": null\n" +
            "            },\n" +
            "            \"flickr\": {\n" +
            "                \"small\": [],\n" +
            "                \"original\": [\"https://live.staticflickr.com/65535/51928220502_1a44139be7_o.jpg\"]\n" +
            "            },\n" +
            "            \"presskit\": null,\n" +
            "            \"webcast\": \"https://www.youtube.com/watch?v=v0w9p3U8860\",\n" +
            "            \"youtube_id\": \"v0w9p3U8860\",\n" +
            "            \"article\": \"http://www.spacex.com/news/2013/02/11/falcon-1-flight-3-mission-summary\",\n" +
            "            \"wikipedia\": \"https://en.wikipedia.org/wiki/Trailblazer_(satellite)\"\n" +
            "        },\n" +
            "        \"static_fire_date_utc\": null,\n" +
            "        \"static_fire_date_unix\": null,\n" +
            "        \"net\": false,\n" +
            "        \"window\": 0,\n" +
            "        \"rocket\": \"5e9d0d95eda69955f709d1eb\",\n" +
            "        \"success\": false,\n" +
            "        \"failures\": [\n" +
            "            {\n" +
            "                \"time\": 140,\n" +
            "                \"altitude\": 35,\n" +
            "                \"reason\": \"residual stage-1 thrust led to collision between stage 1 and stage 2\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"details\": \"Residual stage 1 thrust led to collision between stage 1 and stage 2\",\n" +
            "        \"crew\": [],\n" +
            "        \"ships\": [],\n" +
            "        \"capsules\": [],\n" +
            "        \"payloads\": [\n" +
            "            \"5eb0e4b6b6c3bb0006eeb1e3\",\n" +
            "            \"5eb0e4b6b6c3bb0006eeb1e4\"\n" +
            "        ],\n" +
            "        \"launchpad\": \"5e9e4502f5090995de566f86\",\n" +
            "        \"flight_number\": 3,\n" +
            "        \"name\": \"Trailblazer\",\n" +
            "        \"date_utc\": \"2008-08-03T03:34:00.000Z\",\n" +
            "        \"date_unix\": 1217734440,\n" +
            "        \"date_local\": \"2008-08-03T15:34:00+12:00\",\n" +
            "        \"date_precision\": \"hour\",\n" +
            "        \"upcoming\": false,\n" +
            "        \"cores\": [\n" +
            "            {\n" +
            "                \"core\": \"5e9e289ef3591814873b2625\",\n" +
            "                \"flight\": 1,\n" +
            "                \"gridfins\": false,\n" +
            "                \"legs\": false,\n" +
            "                \"reused\": false,\n" +
            "                \"landing_attempt\": false,\n" +
            "                \"landing_success\": null,\n" +
            "                \"landing_type\": null,\n" +
            "                \"landpad\": null\n" +
            "            }\n" +
            "        ],\n" +
            "        \"auto_update\": true,\n" +
            "        \"tbd\": false,\n" +
            "        \"launch_library_id\": null,\n" +
            "        \"id\": \"5eb87cdbffd86e000604b32c\"\n" +
            "    }]"
}