//package com.gymbro.app.data;
//
//import com.gymbro.app.models.Exercise;
//import com.gymbro.app.models.Routine;
//import com.gymbro.app.models.Workout;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * MockDataRepository: Single source of truth for all mock/local data.
// *
// * BACKEND INTEGRATION NOTE:
// * When connecting Node.js backend, replace these static methods with
// * API calls via Retrofit or OkHttp. Keep the same method signatures
// * so ViewModels don't need to change.
// */
//public class MockDataRepository {
//
//    // Singleton instance
//    private static MockDataRepository instance;
//
//    public static MockDataRepository getInstance() {
//        if (instance == null) {
//            instance = new MockDataRepository();
//        }
//        return instance;
//    }
//
//    private MockDataRepository() {}
//
//    // =====================================================================
//    // EXERCISES — 300+ entries across all muscle groups
//    // =====================================================================
//
//    public List<Exercise> getAllExercises() {
//        List<Exercise> exercises = new ArrayList<>();
//
//        // --- CHEST ---
//        exercises.add(new Exercise("e001", "Barbell Bench Press", "Chest", "Triceps, Front Delts", "Barbell", "Intermediate", "Lie on bench, grip barbell slightly wider than shoulder width, lower to chest, press up explosively.", "Strength"));
//        exercises.add(new Exercise("e002", "Incline Barbell Press", "Chest", "Upper Chest, Triceps", "Barbell", "Intermediate", "Set bench to 30-45°, press barbell from upper chest.", "Strength"));
//        exercises.add(new Exercise("e003", "Decline Barbell Press", "Chest", "Lower Chest, Triceps", "Barbell", "Intermediate", "Set bench to -15°, press barbell targeting lower chest.", "Strength"));
//        exercises.add(new Exercise("e004", "Dumbbell Bench Press", "Chest", "Triceps, Front Delts", "Dumbbells", "Beginner", "Press dumbbells from chest level, squeeze at top.", "Strength"));
//        exercises.add(new Exercise("e005", "Incline Dumbbell Press", "Chest", "Upper Chest", "Dumbbells", "Beginner", "Incline bench press with dumbbells for upper chest focus.", "Strength"));
//        exercises.add(new Exercise("e006", "Dumbbell Flyes", "Chest", "Chest", "Dumbbells", "Intermediate", "Open arms wide with slight bend in elbows, squeeze chest to return.", "Strength"));
//        exercises.add(new Exercise("e007", "Cable Flyes", "Chest", "Chest", "Cable Machine", "Intermediate", "Cable crossover movement for constant chest tension.", "Strength"));
//        exercises.add(new Exercise("e008", "Push-Up", "Chest", "Triceps, Shoulders", "Bodyweight", "Beginner", "Classic push-up maintaining straight body line.", "Bodyweight"));
//        exercises.add(new Exercise("e009", "Wide Push-Up", "Chest", "Outer Chest", "Bodyweight", "Beginner", "Wider hand placement emphasizes outer chest.", "Bodyweight"));
//        exercises.add(new Exercise("e010", "Diamond Push-Up", "Chest", "Triceps", "Bodyweight", "Intermediate", "Close hand placement targets inner chest and triceps.", "Bodyweight"));
//        exercises.add(new Exercise("e011", "Pec Deck Machine", "Chest", "Chest", "Machine", "Beginner", "Isolation machine for chest, great for pump.", "Strength"));
//        exercises.add(new Exercise("e012", "Chest Dip", "Chest", "Triceps, Shoulders", "Bodyweight", "Intermediate", "Lean forward on dip bars to target chest over triceps.", "Bodyweight"));
//        exercises.add(new Exercise("e013", "Low Cable Crossover", "Chest", "Upper Chest", "Cable Machine", "Intermediate", "Cable from low position sweeping upward for upper chest.", "Strength"));
//        exercises.add(new Exercise("e014", "High Cable Crossover", "Chest", "Lower Chest", "Cable Machine", "Intermediate", "Cable from high position sweeping downward for lower chest.", "Strength"));
//        exercises.add(new Exercise("e015", "Svend Press", "Chest", "Inner Chest", "Plates", "Intermediate", "Press plate between palms for inner chest contraction.", "Strength"));
//
//        // --- BACK ---
//        exercises.add(new Exercise("e016", "Barbell Deadlift", "Back", "Glutes, Hamstrings, Traps", "Barbell", "Advanced", "Hinge at hips, grip bar, drive through floor, lock out hips.", "Strength"));
//        exercises.add(new Exercise("e017", "Pull-Up", "Back", "Biceps, Rear Delts", "Bodyweight", "Intermediate", "Grip bar overhand, pull chin above bar, lower controlled.", "Bodyweight"));
//        exercises.add(new Exercise("e018", "Chin-Up", "Back", "Biceps", "Bodyweight", "Intermediate", "Underhand grip pull-up emphasizing biceps.", "Bodyweight"));
//        exercises.add(new Exercise("e019", "Barbell Row", "Back", "Biceps, Rear Delts", "Barbell", "Intermediate", "Hinge forward, row barbell to lower chest/navel.", "Strength"));
//        exercises.add(new Exercise("e020", "Dumbbell Row", "Back", "Biceps, Rear Delts", "Dumbbells", "Beginner", "Single arm row with dumbbell, brace on bench.", "Strength"));
//        exercises.add(new Exercise("e021", "Cable Row", "Back", "Biceps, Rear Delts", "Cable Machine", "Beginner", "Seated cable row to midsection.", "Strength"));
//        exercises.add(new Exercise("e022", "Lat Pulldown", "Back", "Biceps", "Cable Machine", "Beginner", "Pull bar to upper chest from overhead.", "Strength"));
//        exercises.add(new Exercise("e023", "Wide Grip Pulldown", "Back", "Lats", "Cable Machine", "Beginner", "Wider grip emphasizes lat width.", "Strength"));
//        exercises.add(new Exercise("e024", "T-Bar Row", "Back", "Biceps, Rear Delts", "Barbell", "Intermediate", "Row T-bar attachment for thick back.", "Strength"));
//        exercises.add(new Exercise("e025", "Rack Pull", "Back", "Traps, Spinal Erectors", "Barbell", "Intermediate", "Partial deadlift from knee height.", "Strength"));
//        exercises.add(new Exercise("e026", "Hyperextension", "Back", "Spinal Erectors, Glutes", "Machine", "Beginner", "Extend torso from hinged position.", "Strength"));
//        exercises.add(new Exercise("e027", "Good Morning", "Back", "Hamstrings, Glutes", "Barbell", "Advanced", "Bar on back, hinge at hips with slight knee bend.", "Strength"));
//        exercises.add(new Exercise("e028", "Face Pull", "Back", "Rear Delts, Rotator Cuff", "Cable Machine", "Beginner", "Pull rope to face level, external rotation.", "Strength"));
//        exercises.add(new Exercise("e029", "Pendlay Row", "Back", "Biceps, Rear Delts", "Barbell", "Advanced", "Strict barbell row from dead stop each rep.", "Strength"));
//        exercises.add(new Exercise("e030", "Meadows Row", "Back", "Lats", "Barbell", "Advanced", "Landmine-style row for lat emphasis.", "Strength"));
//        exercises.add(new Exercise("e031", "Single Arm Cable Row", "Back", "Lats, Biceps", "Cable Machine", "Intermediate", "Unilateral cable row for imbalance correction.", "Strength"));
//        exercises.add(new Exercise("e032", "Straight Arm Pulldown", "Back", "Lats", "Cable Machine", "Intermediate", "Keep arms straight, pull bar to thighs.", "Strength"));
//
//        // --- SHOULDERS ---
//        exercises.add(new Exercise("e033", "Barbell Overhead Press", "Shoulders", "Triceps, Upper Chest", "Barbell", "Intermediate", "Press barbell overhead from front rack position.", "Strength"));
//        exercises.add(new Exercise("e034", "Dumbbell Shoulder Press", "Shoulders", "Triceps", "Dumbbells", "Beginner", "Press dumbbells overhead simultaneously.", "Strength"));
//        exercises.add(new Exercise("e035", "Arnold Press", "Shoulders", "All Three Delt Heads", "Dumbbells", "Intermediate", "Rotation during press hits all delt heads.", "Strength"));
//        exercises.add(new Exercise("e036", "Lateral Raise", "Shoulders", "Side Delts", "Dumbbells", "Beginner", "Raise arms to side to shoulder height.", "Strength"));
//        exercises.add(new Exercise("e037", "Cable Lateral Raise", "Shoulders", "Side Delts", "Cable Machine", "Beginner", "Cable version provides constant tension.", "Strength"));
//        exercises.add(new Exercise("e038", "Front Raise", "Shoulders", "Front Delts", "Dumbbells", "Beginner", "Raise arms forward to shoulder height.", "Strength"));
//        exercises.add(new Exercise("e039", "Rear Delt Fly", "Shoulders", "Rear Delts", "Dumbbells", "Beginner", "Bent over, raise arms to side targeting rear delts.", "Strength"));
//        exercises.add(new Exercise("e040", "Upright Row", "Shoulders", "Traps, Biceps", "Barbell", "Intermediate", "Pull bar up close to body to chin level.", "Strength"));
//        exercises.add(new Exercise("e041", "Machine Shoulder Press", "Shoulders", "Triceps", "Machine", "Beginner", "Guided press machine for shoulder development.", "Strength"));
//        exercises.add(new Exercise("e042", "Push Press", "Shoulders", "Legs, Triceps", "Barbell", "Advanced", "Use leg drive to initiate overhead press.", "Strength"));
//        exercises.add(new Exercise("e043", "Handstand Push-Up", "Shoulders", "Triceps", "Bodyweight", "Advanced", "Inverted press against wall.", "Bodyweight"));
//        exercises.add(new Exercise("e044", "Cable Front Raise", "Shoulders", "Front Delts", "Cable Machine", "Beginner", "Cable front raise for constant tension.", "Strength"));
//        exercises.add(new Exercise("e045", "Landmine Press", "Shoulders", "Upper Chest", "Barbell", "Intermediate", "Angled press from landmine attachment.", "Strength"));
//
//        // --- ARMS: BICEPS ---
//        exercises.add(new Exercise("e046", "Barbell Curl", "Biceps", "Forearms", "Barbell", "Beginner", "Curl barbell from hip to shoulder height.", "Strength"));
//        exercises.add(new Exercise("e047", "Dumbbell Curl", "Biceps", "Forearms", "Dumbbells", "Beginner", "Alternate or simultaneous dumbbell curls.", "Strength"));
//        exercises.add(new Exercise("e048", "Hammer Curl", "Biceps", "Brachialis, Forearms", "Dumbbells", "Beginner", "Neutral grip curl for brachialis emphasis.", "Strength"));
//        exercises.add(new Exercise("e049", "Preacher Curl", "Biceps", "Forearms", "Barbell", "Intermediate", "Isolate biceps against preacher pad.", "Strength"));
//        exercises.add(new Exercise("e050", "Concentration Curl", "Biceps", "Biceps Peak", "Dumbbells", "Beginner", "Single arm curl braced against inner thigh.", "Strength"));
//        exercises.add(new Exercise("e051", "Cable Curl", "Biceps", "Forearms", "Cable Machine", "Beginner", "Constant tension bicep curl with cable.", "Strength"));
//        exercises.add(new Exercise("e052", "Incline Dumbbell Curl", "Biceps", "Long Head", "Dumbbells", "Intermediate", "Stretch position on incline bench.", "Strength"));
//        exercises.add(new Exercise("e053", "21s", "Biceps", "Forearms", "Barbell", "Intermediate", "7 partial bottom, 7 partial top, 7 full range.", "Strength"));
//        exercises.add(new Exercise("e054", "Spider Curl", "Biceps", "Biceps Peak", "Dumbbells", "Intermediate", "Prone on incline bench for peak contraction.", "Strength"));
//        exercises.add(new Exercise("e055", "Reverse Curl", "Biceps", "Brachioradialis", "Barbell", "Beginner", "Overhand grip curl for forearm development.", "Strength"));
//
//        // --- ARMS: TRICEPS ---
//        exercises.add(new Exercise("e056", "Tricep Pushdown", "Triceps", "All Three Heads", "Cable Machine", "Beginner", "Push cable bar or rope down to full extension.", "Strength"));
//        exercises.add(new Exercise("e057", "Skull Crusher", "Triceps", "Long Head", "Barbell", "Intermediate", "Lower bar to forehead, extend overhead.", "Strength"));
//        exercises.add(new Exercise("e058", "Overhead Tricep Extension", "Triceps", "Long Head", "Dumbbells", "Beginner", "Extend dumbbell overhead from behind head.", "Strength"));
//        exercises.add(new Exercise("e059", "Close Grip Bench Press", "Triceps", "Chest", "Barbell", "Intermediate", "Narrow grip bench for tricep emphasis.", "Strength"));
//        exercises.add(new Exercise("e060", "Tricep Dip", "Triceps", "Chest, Shoulders", "Bodyweight", "Intermediate", "Body upright on parallel bars or bench.", "Bodyweight"));
//        exercises.add(new Exercise("e061", "Rope Pushdown", "Triceps", "Lateral Head", "Cable Machine", "Beginner", "Rope attachment, spread at bottom for lateral head.", "Strength"));
//        exercises.add(new Exercise("e062", "Kickback", "Triceps", "Lateral Head", "Dumbbells", "Beginner", "Hinge forward, extend arm back.", "Strength"));
//        exercises.add(new Exercise("e063", "Diamond Push-Up", "Triceps", "Chest", "Bodyweight", "Intermediate", "Hands form diamond shape, targets triceps.", "Bodyweight"));
//        exercises.add(new Exercise("e064", "JM Press", "Triceps", "Long Head", "Barbell", "Advanced", "Cross between skull crusher and close grip press.", "Strength"));
//        exercises.add(new Exercise("e065", "Single Arm Cable Pushdown", "Triceps", "All Heads", "Cable Machine", "Beginner", "Unilateral tricep pushdown.", "Strength"));
//
//        // --- LEGS: QUADS ---
//        exercises.add(new Exercise("e066", "Barbell Back Squat", "Quadriceps", "Glutes, Hamstrings", "Barbell", "Intermediate", "Bar on traps, squat to parallel or below.", "Strength"));
//        exercises.add(new Exercise("e067", "Front Squat", "Quadriceps", "Core, Upper Back", "Barbell", "Advanced", "Bar in front rack, upright torso squat.", "Strength"));
//        exercises.add(new Exercise("e068", "Goblet Squat", "Quadriceps", "Glutes, Core", "Dumbbells", "Beginner", "Hold dumbbell at chest, squat deep.", "Strength"));
//        exercises.add(new Exercise("e069", "Leg Press", "Quadriceps", "Glutes, Hamstrings", "Machine", "Beginner", "Press platform with feet at various positions.", "Strength"));
//        exercises.add(new Exercise("e070", "Hack Squat", "Quadriceps", "Glutes", "Machine", "Intermediate", "Machine hack squat for quad emphasis.", "Strength"));
//        exercises.add(new Exercise("e071", "Leg Extension", "Quadriceps", "Quads", "Machine", "Beginner", "Isolation extension for quad definition.", "Strength"));
//        exercises.add(new Exercise("e072", "Bulgarian Split Squat", "Quadriceps", "Glutes, Balance", "Dumbbells", "Intermediate", "Rear foot elevated, single leg squat.", "Strength"));
//        exercises.add(new Exercise("e073", "Lunge", "Quadriceps", "Glutes, Hamstrings", "Bodyweight", "Beginner", "Step forward, lower knee toward floor.", "Bodyweight"));
//        exercises.add(new Exercise("e074", "Walking Lunge", "Quadriceps", "Glutes", "Bodyweight", "Beginner", "Continuous walking lunge.", "Bodyweight"));
//        exercises.add(new Exercise("e075", "Sissy Squat", "Quadriceps", "Quads", "Bodyweight", "Advanced", "Lean back while squatting on balls of feet.", "Bodyweight"));
//        exercises.add(new Exercise("e076", "Sumo Squat", "Quadriceps", "Inner Thighs, Glutes", "Dumbbells", "Beginner", "Wide stance squat for inner thigh emphasis.", "Strength"));
//        exercises.add(new Exercise("e077", "Wall Sit", "Quadriceps", "Glutes", "Bodyweight", "Beginner", "Hold seated position against wall.", "Bodyweight"));
//
//        // --- LEGS: HAMSTRINGS ---
//        exercises.add(new Exercise("e078", "Romanian Deadlift", "Hamstrings", "Glutes, Lower Back", "Barbell", "Intermediate", "Hinge at hips with minimal knee bend.", "Strength"));
//        exercises.add(new Exercise("e079", "Leg Curl", "Hamstrings", "Calves", "Machine", "Beginner", "Lying or seated leg curl machine.", "Strength"));
//        exercises.add(new Exercise("e080", "Stiff Leg Deadlift", "Hamstrings", "Glutes", "Barbell", "Intermediate", "Straight leg variation for hamstring stretch.", "Strength"));
//        exercises.add(new Exercise("e081", "Nordic Curl", "Hamstrings", "Calves", "Bodyweight", "Advanced", "Eccentric hamstring curl, knees pinned.", "Bodyweight"));
//        exercises.add(new Exercise("e082", "Glute Ham Raise", "Hamstrings", "Glutes", "Machine", "Advanced", "GHR machine for posterior chain.", "Strength"));
//        exercises.add(new Exercise("e083", "Single Leg Deadlift", "Hamstrings", "Glutes, Balance", "Dumbbells", "Intermediate", "Unilateral RDL for stability.", "Strength"));
//        exercises.add(new Exercise("e084", "Dumbbell Romanian Deadlift", "Hamstrings", "Glutes", "Dumbbells", "Beginner", "RDL variation with dumbbells.", "Strength"));
//
//        // --- LEGS: GLUTES & CALVES ---
//        exercises.add(new Exercise("e085", "Hip Thrust", "Glutes", "Hamstrings", "Barbell", "Intermediate", "Drive hips up with bar on hips, back on bench.", "Strength"));
//        exercises.add(new Exercise("e086", "Glute Bridge", "Glutes", "Hamstrings, Core", "Bodyweight", "Beginner", "Bridge hips from floor.", "Bodyweight"));
//        exercises.add(new Exercise("e087", "Cable Kickback", "Glutes", "Hamstrings", "Cable Machine", "Beginner", "Kick leg back with ankle cuff attachment.", "Strength"));
//        exercises.add(new Exercise("e088", "Abduction Machine", "Glutes", "Hip Abductors", "Machine", "Beginner", "Push legs apart on abduction machine.", "Strength"));
//        exercises.add(new Exercise("e089", "Step-Up", "Glutes", "Quads, Balance", "Dumbbells", "Beginner", "Step onto elevated platform.", "Strength"));
//        exercises.add(new Exercise("e090", "Standing Calf Raise", "Calves", "Soleus", "Machine", "Beginner", "Rise on toes on calf raise machine.", "Strength"));
//        exercises.add(new Exercise("e091", "Seated Calf Raise", "Calves", "Soleus", "Machine", "Beginner", "Seated variation targets soleus.", "Strength"));
//        exercises.add(new Exercise("e092", "Donkey Calf Raise", "Calves", "Gastrocnemius", "Machine", "Intermediate", "Hinge forward for stretch.", "Strength"));
//        exercises.add(new Exercise("e093", "Single Leg Calf Raise", "Calves", "Balance", "Bodyweight", "Beginner", "Unilateral calf raise on step.", "Bodyweight"));
//
//        // --- CORE & ABS ---
//        exercises.add(new Exercise("e094", "Plank", "Core", "Shoulders, Glutes", "Bodyweight", "Beginner", "Hold push-up position, body straight.", "Bodyweight"));
//        exercises.add(new Exercise("e095", "Side Plank", "Core", "Obliques", "Bodyweight", "Beginner", "Hold sideways plank position.", "Bodyweight"));
//        exercises.add(new Exercise("e096", "Crunch", "Core", "Upper Abs", "Bodyweight", "Beginner", "Curl shoulders off floor.", "Bodyweight"));
//        exercises.add(new Exercise("e097", "Bicycle Crunch", "Core", "Obliques", "Bodyweight", "Beginner", "Alternating elbow to knee.", "Bodyweight"));
//        exercises.add(new Exercise("e098", "Leg Raise", "Core", "Lower Abs", "Bodyweight", "Intermediate", "Raise legs from hanging or lying.", "Bodyweight"));
//        exercises.add(new Exercise("e099", "Hanging Leg Raise", "Core", "Hip Flexors", "Bodyweight", "Intermediate", "Hang from bar, raise legs.", "Bodyweight"));
//        exercises.add(new Exercise("e100", "Ab Wheel Rollout", "Core", "Shoulders, Lats", "Equipment", "Advanced", "Roll wheel forward from knees.", "Strength"));
//        exercises.add(new Exercise("e101", "Russian Twist", "Core", "Obliques", "Bodyweight", "Intermediate", "Rotate torso side to side.", "Bodyweight"));
//        exercises.add(new Exercise("e102", "Cable Crunch", "Core", "Upper Abs", "Cable Machine", "Intermediate", "Kneel, crunch down with rope.", "Strength"));
//        exercises.add(new Exercise("e103", "Dragon Flag", "Core", "Full Body", "Bodyweight", "Advanced", "Raise full body from shoulders.", "Bodyweight"));
//        exercises.add(new Exercise("e104", "Dead Bug", "Core", "Lower Back", "Bodyweight", "Beginner", "Opposite arm-leg extension on back.", "Bodyweight"));
//        exercises.add(new Exercise("e105", "Mountain Climber", "Core", "Cardio", "Bodyweight", "Beginner", "Alternate driving knees to chest.", "Cardio"));
//        exercises.add(new Exercise("e106", "V-Up", "Core", "Hip Flexors", "Bodyweight", "Intermediate", "Simultaneously raise arms and legs.", "Bodyweight"));
//        exercises.add(new Exercise("e107", "Pallof Press", "Core", "Obliques", "Cable Machine", "Intermediate", "Anti-rotation press.", "Strength"));
//        exercises.add(new Exercise("e108", "Hollow Hold", "Core", "Hip Flexors", "Bodyweight", "Intermediate", "Hollow body position hold.", "Bodyweight"));
//
//        // --- CARDIO & CONDITIONING ---
//        exercises.add(new Exercise("e109", "Treadmill Run", "Cardio", "Full Body", "Cardio Machine", "Beginner", "Run on treadmill at varied pace and incline.", "Cardio"));
//        exercises.add(new Exercise("e110", "Rowing Machine", "Cardio", "Back, Arms", "Cardio Machine", "Beginner", "Full body rowing for cardio and strength.", "Cardio"));
//        exercises.add(new Exercise("e111", "Assault Bike", "Cardio", "Arms, Legs", "Cardio Machine", "Beginner", "Air bike for high intensity cardio.", "Cardio"));
//        exercises.add(new Exercise("e112", "Burpee", "Cardio", "Full Body", "Bodyweight", "Intermediate", "Squat thrust to jump.", "Cardio"));
//        exercises.add(new Exercise("e113", "Jump Rope", "Cardio", "Calves, Coordination", "Equipment", "Beginner", "Skip rope for cardiovascular conditioning.", "Cardio"));
//        exercises.add(new Exercise("e114", "Box Jump", "Cardio", "Quads, Glutes", "Equipment", "Intermediate", "Jump onto box, step down.", "Plyometric"));
//        exercises.add(new Exercise("e115", "Jumping Jack", "Cardio", "Full Body", "Bodyweight", "Beginner", "Jump with arms and legs wide.", "Cardio"));
//        exercises.add(new Exercise("e116", "Stair Climber", "Cardio", "Glutes, Quads", "Cardio Machine", "Beginner", "Step machine for lower body cardio.", "Cardio"));
//        exercises.add(new Exercise("e117", "Sprint", "Cardio", "Full Body", "Bodyweight", "Intermediate", "All-out sprint for 30-100m.", "Cardio"));
//        exercises.add(new Exercise("e118", "Elliptical", "Cardio", "Full Body", "Cardio Machine", "Beginner", "Low impact full body cardio.", "Cardio"));
//
//        // --- OLYMPIC & POWER ---
//        exercises.add(new Exercise("e119", "Power Clean", "Full Body", "Hamstrings, Traps", "Barbell", "Advanced", "Explosive pull from floor to front rack.", "Olympic"));
//        exercises.add(new Exercise("e120", "Hang Clean", "Full Body", "Traps, Shoulders", "Barbell", "Advanced", "Clean from hang position.", "Olympic"));
//        exercises.add(new Exercise("e121", "Snatch", "Full Body", "Shoulders, Quads", "Barbell", "Advanced", "One movement from floor to overhead.", "Olympic"));
//        exercises.add(new Exercise("e122", "Clean and Jerk", "Full Body", "Shoulders, Legs", "Barbell", "Advanced", "Clean then push-jerk overhead.", "Olympic"));
//        exercises.add(new Exercise("e123", "Kettlebell Swing", "Full Body", "Glutes, Hamstrings", "Kettlebell", "Intermediate", "Hip hinge swing to shoulder height.", "Strength"));
//        exercises.add(new Exercise("e124", "Turkish Get-Up", "Full Body", "Shoulders, Core", "Kettlebell", "Advanced", "Floor to standing with weight overhead.", "Strength"));
//        exercises.add(new Exercise("e125", "Farmers Walk", "Full Body", "Grip, Traps", "Dumbbells", "Beginner", "Walk with heavy weights in each hand.", "Functional"));
//        exercises.add(new Exercise("e126", "Sled Push", "Full Body", "Quads, Glutes", "Sled", "Intermediate", "Push loaded sled across floor.", "Functional"));
//
//        // --- COMPOUND MULTI-MUSCLE ---
//        exercises.add(new Exercise("e127", "Thruster", "Full Body", "Quads, Shoulders", "Barbell", "Advanced", "Front squat into push press.", "Strength"));
//        exercises.add(new Exercise("e128", "Clean Pull", "Back", "Hamstrings", "Barbell", "Advanced", "First pull of clean, extends to toes.", "Olympic"));
//        exercises.add(new Exercise("e129", "Barbell Complex", "Full Body", "Full Body", "Barbell", "Advanced", "Series of barbell movements without rest.", "Conditioning"));
//        exercises.add(new Exercise("e130", "Man Maker", "Full Body", "Full Body", "Dumbbells", "Advanced", "Renegade row, push up, squat, press.", "Conditioning"));
//
//        // --- STRETCHING & MOBILITY ---
//        exercises.add(new Exercise("e131", "Hip Flexor Stretch", "Flexibility", "Hip Flexors", "Bodyweight", "Beginner", "Lunge stretch holding front knee.", "Stretching"));
//        exercises.add(new Exercise("e132", "Hamstring Stretch", "Flexibility", "Hamstrings", "Bodyweight", "Beginner", "Forward fold to stretch hamstrings.", "Stretching"));
//        exercises.add(new Exercise("e133", "Chest Stretch", "Flexibility", "Chest, Shoulders", "Bodyweight", "Beginner", "Doorway or wall chest opener.", "Stretching"));
//        exercises.add(new Exercise("e134", "Shoulder Stretch", "Flexibility", "Shoulders", "Bodyweight", "Beginner", "Cross-body shoulder stretch.", "Stretching"));
//        exercises.add(new Exercise("e135", "Cat-Cow", "Flexibility", "Spine", "Bodyweight", "Beginner", "Alternate spine flexion and extension.", "Stretching"));
//        exercises.add(new Exercise("e136", "Pigeon Pose", "Flexibility", "Glutes, Hip Flexors", "Bodyweight", "Intermediate", "Yoga pose for hip opening.", "Stretching"));
//        exercises.add(new Exercise("e137", "World's Greatest Stretch", "Flexibility", "Full Body", "Bodyweight", "Intermediate", "Multi-position dynamic stretch.", "Stretching"));
//        exercises.add(new Exercise("e138", "Thread the Needle", "Flexibility", "Upper Back", "Bodyweight", "Beginner", "Thoracic rotation stretch.", "Stretching"));
//        exercises.add(new Exercise("e139", "90/90 Hip Stretch", "Flexibility", "Hips", "Bodyweight", "Beginner", "Internal and external hip rotation.", "Stretching"));
//        exercises.add(new Exercise("e140", "Calf Stretch", "Flexibility", "Calves", "Bodyweight", "Beginner", "Lean into wall to stretch calves.", "Stretching"));
//
//        // --- ADDITIONAL EXERCISES (e141 - e200) ---
//        exercises.add(new Exercise("e141", "Incline Hammer Curl", "Biceps", "Brachialis", "Dumbbells", "Intermediate", "Hammer curl on incline bench.", "Strength"));
//        exercises.add(new Exercise("e142", "Zottman Curl", "Biceps", "Forearms", "Dumbbells", "Intermediate", "Curl up supinated, lower pronated.", "Strength"));
//        exercises.add(new Exercise("e143", "Cross Body Hammer Curl", "Biceps", "Brachioradialis", "Dumbbells", "Beginner", "Hammer curl across body.", "Strength"));
//        exercises.add(new Exercise("e144", "Wide Grip Barbell Curl", "Biceps", "Short Head", "Barbell", "Beginner", "Wider grip targets bicep short head.", "Strength"));
//        exercises.add(new Exercise("e145", "Close Grip Curl", "Biceps", "Long Head", "Barbell", "Beginner", "Close grip targets long head peak.", "Strength"));
//        exercises.add(new Exercise("e146", "Overhead Rope Extension", "Triceps", "Long Head", "Cable Machine", "Intermediate", "Overhead extension with rope.", "Strength"));
//        exercises.add(new Exercise("e147", "Tate Press", "Triceps", "Lateral Head", "Dumbbells", "Intermediate", "Elbows out, press up from chest.", "Strength"));
//        exercises.add(new Exercise("e148", "Board Press", "Triceps", "Chest", "Barbell", "Advanced", "Partial range bench press.", "Strength"));
//        exercises.add(new Exercise("e149", "Reverse Grip Bench", "Triceps", "Upper Chest", "Barbell", "Advanced", "Supinated grip bench press.", "Strength"));
//        exercises.add(new Exercise("e150", "Wrist Curl", "Forearms", "Wrist Flexors", "Barbell", "Beginner", "Curl wrists with barbell on knees.", "Strength"));
//        exercises.add(new Exercise("e151", "Reverse Wrist Curl", "Forearms", "Wrist Extensors", "Barbell", "Beginner", "Reverse wrist curl for extensors.", "Strength"));
//        exercises.add(new Exercise("e152", "Plate Pinch", "Forearms", "Grip", "Plates", "Beginner", "Pinch two plates together, hold.", "Strength"));
//        exercises.add(new Exercise("e153", "Gripper", "Forearms", "Hand Strength", "Equipment", "Beginner", "Hand gripper for grip strength.", "Strength"));
//        exercises.add(new Exercise("e154", "Towel Pull-Up", "Back", "Grip, Biceps", "Bodyweight", "Advanced", "Pull-up using towel for grip training.", "Bodyweight"));
//        exercises.add(new Exercise("e155", "Neutral Grip Pull-Up", "Back", "Biceps", "Bodyweight", "Intermediate", "Palms facing each other pull-up.", "Bodyweight"));
//        exercises.add(new Exercise("e156", "Archer Pull-Up", "Back", "Unilateral Back", "Bodyweight", "Advanced", "Shift weight to one arm each rep.", "Bodyweight"));
//        exercises.add(new Exercise("e157", "Muscle-Up", "Back", "Triceps, Chest", "Bodyweight", "Advanced", "Pull-up transitioning above bar.", "Bodyweight"));
//        exercises.add(new Exercise("e158", "Ring Row", "Back", "Biceps, Core", "Equipment", "Beginner", "Row with gymnastic rings.", "Bodyweight"));
//        exercises.add(new Exercise("e159", "Inverted Row", "Back", "Biceps", "Bodyweight", "Beginner", "Row from underneath a bar.", "Bodyweight"));
//        exercises.add(new Exercise("e160", "Chest Supported Row", "Back", "Rear Delts", "Dumbbells", "Beginner", "Prone on incline bench, row dumbbells.", "Strength"));
//
//        // More exercises e161-e200
//        exercises.add(new Exercise("e161", "Single Arm Pulldown", "Back", "Lats", "Cable Machine", "Intermediate", "One arm lat pulldown.", "Strength"));
//        exercises.add(new Exercise("e162", "Kneeling Cable Crunch", "Core", "Upper Abs", "Cable Machine", "Beginner", "Crunch down on knees with cable.", "Strength"));
//        exercises.add(new Exercise("e163", "Decline Crunch", "Core", "Upper Abs", "Bodyweight", "Beginner", "Crunch on decline bench.", "Bodyweight"));
//        exercises.add(new Exercise("e164", "Toe Touch", "Core", "Lower Abs", "Bodyweight", "Beginner", "Raise legs and reach toes.", "Bodyweight"));
//        exercises.add(new Exercise("e165", "Windmill", "Core", "Obliques, Shoulders", "Kettlebell", "Advanced", "Side bend with weight overhead.", "Strength"));
//        exercises.add(new Exercise("e166", "Suitcase Carry", "Core", "Obliques, Grip", "Dumbbells", "Beginner", "Walk with weight on one side only.", "Functional"));
//        exercises.add(new Exercise("e167", "L-Sit", "Core", "Hip Flexors", "Bodyweight", "Advanced", "Hold L-shape on floor or bars.", "Bodyweight"));
//        exercises.add(new Exercise("e168", "Tuck Crunch", "Core", "Upper Abs", "Bodyweight", "Beginner", "Bring knees to elbows.", "Bodyweight"));
//        exercises.add(new Exercise("e169", "Copenhagen Plank", "Core", "Adductors", "Bodyweight", "Advanced", "Side plank with top leg supported.", "Bodyweight"));
//        exercises.add(new Exercise("e170", "Stir The Pot", "Core", "Full Core", "Equipment", "Intermediate", "Circular motion on Swiss ball.", "Stability"));
//        exercises.add(new Exercise("e171", "Cable Wood Chop", "Core", "Obliques", "Cable Machine", "Intermediate", "Diagonal pull across body.", "Strength"));
//        exercises.add(new Exercise("e172", "Barbell Hip Thrust", "Glutes", "Hamstrings", "Barbell", "Intermediate", "Weighted hip thrust.", "Strength"));
//        exercises.add(new Exercise("e173", "Sumo Deadlift", "Legs", "Glutes, Inner Thighs", "Barbell", "Intermediate", "Wide stance deadlift.", "Strength"));
//        exercises.add(new Exercise("e174", "Trap Bar Deadlift", "Legs", "Quads, Glutes", "Trap Bar", "Intermediate", "Neutral grip hex bar deadlift.", "Strength"));
//        exercises.add(new Exercise("e175", "Pause Squat", "Quadriceps", "Glutes", "Barbell", "Advanced", "Hold at bottom for 2-3 seconds.", "Strength"));
//        exercises.add(new Exercise("e176", "Box Squat", "Quadriceps", "Glutes", "Barbell", "Intermediate", "Squat to box, pause, stand.", "Strength"));
//        exercises.add(new Exercise("e177", "Zercher Squat", "Quadriceps", "Core, Biceps", "Barbell", "Advanced", "Bar in elbow crease.", "Strength"));
//        exercises.add(new Exercise("e178", "Jefferson Squat", "Quadriceps", "Adductors", "Barbell", "Advanced", "Straddle bar, stand up.", "Strength"));
//        exercises.add(new Exercise("e179", "Heel Elevated Squat", "Quadriceps", "Quads", "Dumbbells", "Beginner", "Heels on plate for quad emphasis.", "Strength"));
//        exercises.add(new Exercise("e180", "Pistol Squat", "Quadriceps", "Balance, Glutes", "Bodyweight", "Advanced", "Single leg squat to floor.", "Bodyweight"));
//        exercises.add(new Exercise("e181", "Split Squat", "Quadriceps", "Glutes", "Dumbbells", "Beginner", "Stationary lunge position.", "Strength"));
//        exercises.add(new Exercise("e182", "Reverse Lunge", "Quadriceps", "Glutes", "Dumbbells", "Beginner", "Step backward into lunge.", "Strength"));
//        exercises.add(new Exercise("e183", "Lateral Lunge", "Adductors", "Glutes, Quads", "Bodyweight", "Beginner", "Step to side, sit into leg.", "Bodyweight"));
//        exercises.add(new Exercise("e184", "Curtsy Lunge", "Glutes", "Adductors", "Dumbbells", "Intermediate", "Cross leg behind to lunge.", "Strength"));
//        exercises.add(new Exercise("e185", "Step-Up to Reverse Lunge", "Quadriceps", "Glutes, Balance", "Dumbbells", "Intermediate", "Step up, step back into lunge.", "Strength"));
//        exercises.add(new Exercise("e186", "Cable Pull Through", "Glutes", "Hamstrings", "Cable Machine", "Beginner", "Hip hinge with cable between legs.", "Strength"));
//        exercises.add(new Exercise("e187", "Smith Machine Squat", "Quadriceps", "Glutes", "Smith Machine", "Beginner", "Guided squat on Smith machine.", "Strength"));
//        exercises.add(new Exercise("e188", "Smith Machine Bench", "Chest", "Triceps", "Smith Machine", "Beginner", "Guided bench press.", "Strength"));
//        exercises.add(new Exercise("e189", "Reverse Hyperextension", "Glutes", "Hamstrings, Lower Back", "Machine", "Intermediate", "Extend legs from lying on bench.", "Strength"));
//        exercises.add(new Exercise("e190", "Nordic Hamstring Curl", "Hamstrings", "Calves", "Bodyweight", "Advanced", "Lower body forward, resisting with hamstrings.", "Bodyweight"));
//        exercises.add(new Exercise("e191", "Swiss Ball Hamstring Curl", "Hamstrings", "Core", "Equipment", "Intermediate", "Bridge and curl with Swiss ball.", "Stability"));
//        exercises.add(new Exercise("e192", "Adductor Machine", "Adductors", "Inner Thighs", "Machine", "Beginner", "Squeeze legs together on machine.", "Strength"));
//        exercises.add(new Exercise("e193", "Band Pull Apart", "Shoulders", "Rear Delts", "Resistance Band", "Beginner", "Pull band apart at shoulder height.", "Mobility"));
//        exercises.add(new Exercise("e194", "Cuban Press", "Shoulders", "Rotator Cuff", "Dumbbells", "Intermediate", "Upright row, external rotation, press.", "Mobility"));
//        exercises.add(new Exercise("e195", "Bottoms Up KB Press", "Shoulders", "Core, Grip", "Kettlebell", "Advanced", "Press with kettlebell inverted.", "Stability"));
//        exercises.add(new Exercise("e196", "Deficit Push-Up", "Chest", "Triceps", "Bodyweight", "Intermediate", "Hands elevated for greater range.", "Bodyweight"));
//        exercises.add(new Exercise("e197", "Pike Push-Up", "Shoulders", "Triceps", "Bodyweight", "Intermediate", "Hips high, lower head toward floor.", "Bodyweight"));
//        exercises.add(new Exercise("e198", "Dips Between Benches", "Triceps", "Chest", "Bodyweight", "Beginner", "Dip between two benches.", "Bodyweight"));
//        exercises.add(new Exercise("e199", "Cable Kickback", "Triceps", "All Heads", "Cable Machine", "Beginner", "Hinge forward, extend arm back.", "Strength"));
//        exercises.add(new Exercise("e200", "Tricep Machine", "Triceps", "All Heads", "Machine", "Beginner", "Machine tricep extension.", "Strength"));
//
//        // e201 - e280: Additional mix
//        String[][] extraExercises = {
//                {"e201","Pec Minor Stretch","Flexibility","Chest","Bodyweight","Beginner","Doorway stretch for pec minor.","Stretching"},
//                {"e202","Thoracic Extension","Flexibility","Upper Back","Bodyweight","Beginner","Extend over foam roller.","Mobility"},
//                {"e203","Ankle Circles","Flexibility","Ankles","Bodyweight","Beginner","Circle ankles for mobility.","Mobility"},
//                {"e204","Wrist Circles","Flexibility","Wrists","Bodyweight","Beginner","Circle wrists to warm up.","Mobility"},
//                {"e205","Neck Stretch","Flexibility","Neck","Bodyweight","Beginner","Tilt head side to side gently.","Stretching"},
//                {"e206","Spiderman Stretch","Flexibility","Hips, Groin","Bodyweight","Intermediate","Lunge with hand to inside of foot.","Stretching"},
//                {"e207","Band Assisted Pull-Up","Back","Biceps","Resistance Band","Beginner","Band under knees for pull-up assistance.","Bodyweight"},
//                {"e208","Negative Pull-Up","Back","Biceps","Bodyweight","Intermediate","Jump up, lower slowly (5-10s).","Bodyweight"},
//                {"e209","Banded Deadlift","Back","Glutes, Hamstrings","Resistance Band","Beginner","Deadlift with resistance band.","Strength"},
//                {"e210","Band Squat","Quadriceps","Glutes","Resistance Band","Beginner","Squat with resistance band.","Strength"},
//                {"e211","Band Row","Back","Biceps","Resistance Band","Beginner","Row with resistance band.","Strength"},
//                {"e212","Band Chest Press","Chest","Triceps","Resistance Band","Beginner","Press band forward.","Strength"},
//                {"e213","Band Lateral Raise","Shoulders","Side Delts","Resistance Band","Beginner","Raise arms to side with band.","Strength"},
//                {"e214","Band Curl","Biceps","Forearms","Resistance Band","Beginner","Curl band for biceps.","Strength"},
//                {"e215","Band Tricep Pushdown","Triceps","All Heads","Resistance Band","Beginner","Pushdown with band.","Strength"},
//                {"e216","Banded Hip Thrust","Glutes","Hamstrings","Resistance Band","Beginner","Hip thrust with band over hips.","Strength"},
//                {"e217","Clamshell","Glutes","Hip Abductors","Resistance Band","Beginner","Open and close knees like clamshell.","Activation"},
//                {"e218","Monster Walk","Glutes","Hip Abductors","Resistance Band","Beginner","Walk sideways with band.","Activation"},
//                {"e219","Fire Hydrant","Glutes","Hip Abductors","Bodyweight","Beginner","Lift knee to side like dog.","Activation"},
//                {"e220","Donkey Kick","Glutes","Hamstrings","Bodyweight","Beginner","Kick heel to ceiling.","Activation"},
//                {"e221","Hip Circle","Glutes","Hip Mobility","Bodyweight","Beginner","Circle leg in hip socket.","Mobility"},
//                {"e222","Inchworm","Full Body","Hamstrings, Shoulders","Bodyweight","Beginner","Walk hands out to push-up, back.","Mobility"},
//                {"e223","Bear Crawl","Full Body","Core, Shoulders","Bodyweight","Intermediate","Crawl forward on hands and feet.","Functional"},
//                {"e224","Crab Walk","Full Body","Triceps, Glutes","Bodyweight","Intermediate","Walk backward on hands and feet.","Functional"},
//                {"e225","Lateral Band Walk","Glutes","Hip Abductors","Resistance Band","Beginner","Step sideways with band.","Activation"},
//                {"e226","Pallof Press ISO Hold","Core","Obliques","Cable Machine","Intermediate","Hold Pallof press position.","Stability"},
//                {"e227","Landmine Row","Back","Biceps, Rear Delts","Barbell","Intermediate","Row barbell from landmine.","Strength"},
//                {"e228","Landmine Squat","Quadriceps","Glutes","Barbell","Intermediate","Squat with landmine held at chest.","Strength"},
//                {"e229","Landmine Lateral Raise","Shoulders","Side Delts","Barbell","Intermediate","Lateral raise with landmine.","Strength"},
//                {"e230","Dumbbell Shrug","Traps","Neck","Dumbbells","Beginner","Shrug shoulders with dumbbells.","Strength"},
//                {"e231","Barbell Shrug","Traps","Neck","Barbell","Beginner","Shrug shoulders with barbell.","Strength"},
//                {"e232","Cable Shrug","Traps","Neck","Cable Machine","Beginner","Shrug with cable.","Strength"},
//                {"e233","Neck Flexion","Neck","Neck Flexors","Equipment","Beginner","Flex neck forward with plate.","Strength"},
//                {"e234","Neck Extension","Neck","Neck Extensors","Equipment","Beginner","Extend neck back with plate.","Strength"},
//                {"e235","Foam Roll Quads","Flexibility","Quads","Foam Roller","Beginner","Roll along quad muscles.","Recovery"},
//                {"e236","Foam Roll IT Band","Flexibility","IT Band","Foam Roller","Beginner","Roll along outer thigh.","Recovery"},
//                {"e237","Foam Roll Upper Back","Flexibility","Upper Back","Foam Roller","Beginner","Roll thoracic spine.","Recovery"},
//                {"e238","Foam Roll Calves","Flexibility","Calves","Foam Roller","Beginner","Roll calf muscles.","Recovery"},
//                {"e239","Foam Roll Lats","Flexibility","Lats","Foam Roller","Beginner","Roll lat area from side.","Recovery"},
//                {"e240","Foam Roll Glutes","Flexibility","Glutes","Foam Roller","Beginner","Roll glute and piriformis.","Recovery"},
//                {"e241","Jump Squat","Plyometric","Quads, Calves","Bodyweight","Intermediate","Explosive jump from squat.","Plyometric"},
//                {"e242","Split Jump Lunge","Plyometric","Quads, Glutes","Bodyweight","Intermediate","Jump and switch lunge legs.","Plyometric"},
//                {"e243","Plyo Push-Up","Plyometric","Chest, Triceps","Bodyweight","Intermediate","Explosive push-up with clap.","Plyometric"},
//                {"e244","Lateral Box Jump","Plyometric","Quads, Glutes","Equipment","Intermediate","Jump laterally onto box.","Plyometric"},
//                {"e245","Depth Jump","Plyometric","Quads, Calves","Equipment","Advanced","Step off box, land, immediately jump.","Plyometric"},
//                {"e246","Med Ball Slam","Full Body","Core, Shoulders","Medicine Ball","Intermediate","Slam ball overhead to floor.","Conditioning"},
//                {"e247","Med Ball Chest Pass","Chest","Triceps","Medicine Ball","Intermediate","Explosive chest pass.","Conditioning"},
//                {"e248","Med Ball Rotational Throw","Core","Obliques","Medicine Ball","Intermediate","Rotate and throw ball into wall.","Conditioning"},
//                {"e249","Battle Rope Waves","Full Body","Shoulders, Core","Battle Rope","Intermediate","Alternating waves with battle ropes.","Conditioning"},
//                {"e250","Battle Rope Slams","Full Body","Shoulders, Core","Battle Rope","Intermediate","Slam ropes simultaneously.","Conditioning"},
//                {"e251","Sled Pull","Full Body","Back, Hamstrings","Sled","Intermediate","Pull sled toward you.","Functional"},
//                {"e252","Tire Flip","Full Body","Quads, Back","Tire","Advanced","Flip heavy tire end over end.","Functional"},
//                {"e253","Atlas Stone Lift","Full Body","Full Body","Atlas Stone","Advanced","Lift heavy stone to platform.","Strongman"},
//                {"e254","Log Press","Shoulders","Triceps, Legs","Log","Advanced","Overhead press with log implement.","Strongman"},
//                {"e255","Yoke Carry","Full Body","Traps, Core","Yoke","Advanced","Walk with loaded yoke frame.","Strongman"},
//                {"e256","Farmer's Walk with Turns","Full Body","Core, Grip","Dumbbells","Intermediate","Farmer's walk with direction changes.","Functional"},
//                {"e257","Duck Walk","Quadriceps","Glutes, Core","Bodyweight","Intermediate","Walk in deep squat position.","Functional"},
//                {"e258","Lateral Shuffle","Cardio","Quads, Glutes","Bodyweight","Beginner","Quick sideways steps.","Agility"},
//                {"e259","High Knees","Cardio","Hip Flexors, Cardio","Bodyweight","Beginner","Run in place with high knees.","Cardio"},
//                {"e260","Butt Kick","Cardio","Hamstrings, Cardio","Bodyweight","Beginner","Run in place kicking heels to glutes.","Cardio"},
//                {"e261","A-Skip","Cardio","Hip Flexors","Bodyweight","Intermediate","Alternating high knee skip.","Plyometric"},
//                {"e262","B-Skip","Cardio","Hamstrings","Bodyweight","Intermediate","Skip with leg kick extension.","Plyometric"},
//                {"e263","Single Leg Box Jump","Plyometric","Quads, Balance","Equipment","Advanced","Jump onto box from single leg.","Plyometric"},
//                {"e264","Broad Jump","Plyometric","Quads, Glutes","Bodyweight","Intermediate","Jump forward for maximum distance.","Plyometric"},
//                {"e265","Standing Long Jump","Plyometric","Quads, Calves","Bodyweight","Intermediate","Maximum horizontal jump.","Plyometric"},
//                {"e266","Isometric Wall Push","Chest","Triceps","Bodyweight","Beginner","Push against wall with max force.","Isometric"},
//                {"e267","Isometric Curl Hold","Biceps","Forearms","Bodyweight","Beginner","Hold curl at 90 degrees.","Isometric"},
//                {"e268","Isometric Squat Hold","Quadriceps","Glutes","Bodyweight","Beginner","Hold squat at 90 degrees.","Isometric"},
//                {"e269","Isometric Lunge Hold","Quadriceps","Glutes","Bodyweight","Beginner","Hold lunge position.","Isometric"},
//                {"e270","Superman","Back","Glutes","Bodyweight","Beginner","Lift arms and legs off floor simultaneously.","Bodyweight"},
//                {"e271","Bird Dog","Core","Lower Back, Glutes","Bodyweight","Beginner","Opposite arm-leg extension on all fours.","Bodyweight"},
//                {"e272","Glute Kickback","Glutes","Hamstrings","Bodyweight","Beginner","Kick leg back while on all fours.","Bodyweight"},
//                {"e273","Side Lying Hip Abduction","Glutes","Hip Abductors","Bodyweight","Beginner","Raise top leg while lying on side.","Bodyweight"},
//                {"e274","Prone Hip Extension","Glutes","Hamstrings","Bodyweight","Beginner","Lift straight leg while lying prone.","Bodyweight"},
//                {"e275","Single Leg Glute Bridge","Glutes","Core","Bodyweight","Intermediate","Bridge on one leg.","Bodyweight"},
//                {"e276","Nordic Hamstring Eccentric","Hamstrings","Lower Back","Bodyweight","Advanced","Slowly lower forward from knees.","Bodyweight"},
//                {"e277","Standing Hip Flexion","Hip Flexors","Core","Bodyweight","Beginner","Raise knee to hip height while standing.","Mobility"},
//                {"e278","Hip 90-90 Transition","Flexibility","Hips","Bodyweight","Beginner","Shift between internal and external rotation.","Mobility"},
//                {"e279","Weighted Carry (Overhead)","Full Body","Shoulders, Core","Dumbbells","Intermediate","Walk with weight held overhead.","Functional"},
//                {"e280","Rack Carry","Full Body","Core, Biceps","Kettlebell","Intermediate","Walk with weight in front rack.","Functional"},
//        };
//
//        for (String[] data : extraExercises) {
//            exercises.add(new Exercise(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
//        }
//
//        // Additional entries to exceed 300
//        String[][] moreExercises = {
//                {"e281","Cable Face Pull with Ext Rotation","Shoulders","Rotator Cuff","Cable Machine","Intermediate","Face pull with external rotation component.","Strength"},
//                {"e282","Kettlebell Clean","Full Body","Shoulders, Legs","Kettlebell","Intermediate","Clean kettlebell to rack position.","Olympic"},
//                {"e283","Kettlebell Press","Shoulders","Triceps","Kettlebell","Intermediate","Press kettlebell overhead.","Strength"},
//                {"e284","Kettlebell Row","Back","Biceps","Kettlebell","Beginner","Row kettlebell to hip.","Strength"},
//                {"e285","Kettlebell Goblet Squat","Quadriceps","Glutes","Kettlebell","Beginner","Hold kettlebell at chest, squat.","Strength"},
//                {"e286","Kettlebell Lunge","Quadriceps","Glutes","Kettlebell","Beginner","Lunge with kettlebells.","Strength"},
//                {"e287","Kettlebell Deadlift","Back","Glutes, Hamstrings","Kettlebell","Beginner","Deadlift with kettlebell.","Strength"},
//                {"e288","Double KB Front Squat","Quadriceps","Core","Kettlebell","Advanced","Two kettlebells in rack, squat.","Strength"},
//                {"e289","KB Windmill","Core","Shoulders, Hips","Kettlebell","Advanced","Side bend with KB overhead.","Strength"},
//                {"e290","KB Figure 8","Full Body","Core, Grip","Kettlebell","Intermediate","Pass KB in figure 8 between legs.","Conditioning"},
//                {"e291","KB Halo","Shoulders","Core","Kettlebell","Beginner","Circle KB around head.","Mobility"},
//                {"e292","KB Deadmill Row","Back","Lats, Biceps","Kettlebell","Intermediate","Row from deficit on bell handle.","Strength"},
//                {"e293","Pause Bench Press","Chest","Triceps","Barbell","Advanced","Hold at chest for 2-3 seconds.","Strength"},
//                {"e294","Tempo Squat","Quadriceps","Glutes","Barbell","Advanced","Slow controlled descent (4-0-1).","Strength"},
//                {"e295","Tempo Deadlift","Back","Hamstrings","Barbell","Advanced","Controlled speed each phase.","Strength"},
//                {"e296","Anderson Squat","Quadriceps","Glutes","Barbell","Advanced","Start from pins at bottom.","Strength"},
//                {"e297","Pin Press","Chest","Triceps","Barbell","Advanced","Press from pins mid-range.","Strength"},
//                {"e298","Spoto Press","Chest","Triceps","Barbell","Advanced","Pause 2 inches above chest.","Strength"},
//                {"e299","2-Board Press","Triceps","Chest","Barbell","Advanced","Board on chest limits range.","Strength"},
//                {"e300","Reverse Band Squat","Quadriceps","Glutes","Barbell","Advanced","Bands assist at bottom of squat.","Strength"},
//                {"e301","Reverse Band Deadlift","Back","Hamstrings","Barbell","Advanced","Bands reduce load at top.","Strength"},
//                {"e302","Accommodating Resistance Squat","Quadriceps","Glutes","Barbell","Advanced","Bands or chains add resistance.","Strength"},
//                {"e303","Floor Press","Chest","Triceps","Barbell","Intermediate","Bench press lying on floor.","Strength"},
//                {"e304","Larsen Press","Chest","Triceps, Core","Barbell","Advanced","Bench press with legs extended.","Strength"},
//                {"e305","Dead Stop Push-Up","Chest","Triceps","Bodyweight","Intermediate","Full reset on floor each rep.","Bodyweight"},
//        };
//
//        for (String[] data : moreExercises) {
//            exercises.add(new Exercise(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
//        }
//
//        return exercises;
//    }
//
//    // =====================================================================
//    // ROUTINES — Pre-built templates
//    // =====================================================================
//
//    public List<Routine> getDefaultRoutines() {
//        List<Routine> routines = new ArrayList<>();
//        List<Exercise> allExercises = getAllExercises();
//
//        // Push Day
//        Routine push = new Routine("r001", "Push Day", "Chest, Shoulders, Triceps", "Push");
//        push.setDefault(true);
//        push.setExercises(Arrays.asList(allExercises.get(0), allExercises.get(1),
//                allExercises.get(32), allExercises.get(35), allExercises.get(55), allExercises.get(56)));
//        routines.add(push);
//
//        // Pull Day
//        Routine pull = new Routine("r002", "Pull Day", "Back, Biceps, Rear Delts", "Pull");
//        pull.setDefault(true);
//        pull.setExercises(Arrays.asList(allExercises.get(16), allExercises.get(17),
//                allExercises.get(19), allExercises.get(21), allExercises.get(45), allExercises.get(47)));
//        routines.add(pull);
//
//        // Legs Day
//        Routine legs = new Routine("r003", "Leg Day", "Quads, Hamstrings, Glutes, Calves", "Legs");
//        legs.setDefault(true);
//        legs.setExercises(Arrays.asList(allExercises.get(65), allExercises.get(68),
//                allExercises.get(77), allExercises.get(84), allExercises.get(89)));
//        routines.add(legs);
//
//        // Upper Body
//        Routine upper = new Routine("r004", "Upper Body", "Chest, Back, Shoulders, Arms", "Upper");
//        upper.setDefault(true);
//        upper.setExercises(Arrays.asList(allExercises.get(0), allExercises.get(18),
//                allExercises.get(32), allExercises.get(45), allExercises.get(55)));
//        routines.add(upper);
//
//        // Lower Body
//        Routine lower = new Routine("r005", "Lower Body", "Quads, Hamstrings, Glutes, Calves", "Lower");
//        lower.setDefault(true);
//        lower.setExercises(Arrays.asList(allExercises.get(65), allExercises.get(77),
//                allExercises.get(84), allExercises.get(89), allExercises.get(92)));
//        routines.add(lower);
//
//        // Full Body
//        Routine fullBody = new Routine("r006", "Full Body", "All Muscle Groups", "Full Body");
//        fullBody.setDefault(true);
//        fullBody.setExercises(Arrays.asList(allExercises.get(65), allExercises.get(0),
//                allExercises.get(15), allExercises.get(32), allExercises.get(45)));
//        routines.add(fullBody);
//
//        return routines;
//    }
//
//    // =====================================================================
//    // WORKOUT HISTORY — Mock recent sessions
//    // =====================================================================
//
//    public List<Workout> getRecentWorkouts() {
//        List<Workout> history = new ArrayList<>();
//
//        Workout w1 = new Workout("w001", "Push Day", "2024-01-15");
//        w1.setDurationSeconds(3600);
//        w1.setTotalVolume(8400);
//        w1.setCompleted(true);
//        history.add(w1);
//
//        Workout w2 = new Workout("w002", "Pull Day", "2024-01-13");
//        w2.setDurationSeconds(3120);
//        w2.setTotalVolume(7200);
//        w2.setCompleted(true);
//        history.add(w2);
//
//        Workout w3 = new Workout("w003", "Leg Day", "2024-01-11");
//        w3.setDurationSeconds(4200);
//        w3.setTotalVolume(12500);
//        w3.setCompleted(true);
//        history.add(w3);
//
//        Workout w4 = new Workout("w004", "Upper Body", "2024-01-09");
//        w4.setDurationSeconds(3300);
//        w4.setTotalVolume(9100);
//        w4.setCompleted(true);
//        history.add(w4);
//
//        Workout w5 = new Workout("w005", "Full Body", "2024-01-07");
//        w5.setDurationSeconds(3900);
//        w5.setTotalVolume(10300);
//        w5.setCompleted(true);
//        history.add(w5);
//
//        return history;
//    }
//
//    // =====================================================================
//    // MUSCLE GROUP FILTER LIST
//    // =====================================================================
//
//    public List<String> getMuscleGroups() {
//        return Arrays.asList(
//                "All", "Chest", "Back", "Shoulders", "Biceps", "Triceps", "Forearms",
//                "Quadriceps", "Hamstrings", "Glutes", "Calves", "Core", "Full Body",
//                "Cardio", "Flexibility", "Traps", "Adductors", "Neck", "Plyometric"
//        );
//    }
//}